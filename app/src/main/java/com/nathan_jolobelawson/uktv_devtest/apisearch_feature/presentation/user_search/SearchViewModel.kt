package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.user_search

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.data.data_source.RetrofitInstance
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.InvalidResultException
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.use_case.ResultsUseCases
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.util.UserInputType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val resultsUseCases: ResultsUseCases,
) : ViewModel() {

    init {
        // the API is called when the app is opened and then stored in cache.
        // If this feature required storing data long term then we should implement
        // a database to store this data

        Log.d("API CALL", "TRYING TO CALL API")

        if(!resultsUseCases.isResultsCached()) fetchResults()
    }

    private val _userTextFieldState = mutableStateOf(ResultTextFieldState(
        hint = "Enter text: "
    ))
    val userTextFieldState: State<ResultTextFieldState> = _userTextFieldState

    private val _searchResultState = mutableStateOf(SearchResultsState(emptyList()))
    val searchResultState: State<SearchResultsState> = _searchResultState

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun fetchResults() {
        viewModelScope.launch {
            try {
                val filmResponse = RetrofitInstance.api.getFilmResults()
                Log.d("API CALL", "filmResult = $filmResponse")

                val shipsResponse = RetrofitInstance.api.getShipResults()
                Log.d("API CALL", "shipsResult = $shipsResponse")

                val vehicleResponse = RetrofitInstance.api.getVehicleResults()
                Log.d("API CALL", "vehicleResult = $vehicleResponse")

                resultsUseCases.locallyCacheResults(
                    filmResultsData = filmResponse,
                    vehicleResultsData = vehicleResponse,
                    shipsResultsData = shipsResponse)

                if(resultsUseCases.isResultsCached())
                    Log.d("API CALL", "Results have been locally cached!" )
                else {
                    Log.d("API CALL", "Results have not been locally cached! ")
                    throw IllegalArgumentException("RESULTS HAVE NOT BEEN CACHED! ")
                }
            }
            catch(e: IllegalArgumentException) {
                Log.e("IllegalArgumentException", e.message ?: "RESULTS HAVE NOT BEEN CACHED!")
            }
        }
    }

    fun onEvent(event: SearchResultEvent){
        when(event) {
            is SearchResultEvent.OnInputInSearchField -> {
                _userTextFieldState.value = userTextFieldState.value.copy(
                    text = event.value
                )
            }
            is SearchResultEvent.OnChangeSearchFieldFocus -> {
                _userTextFieldState.value = userTextFieldState.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            userTextFieldState.value.text.isBlank()
                )
            }

            // with more time I would like to sort the results with a takeUnless() because right now
            // there'll be duplicate results
            is SearchResultEvent.OnSearchButtonPressed -> {
                val _userInputText =
                    UserInputType.checkUserInput((userTextFieldState.value.text.lowercase()))
                viewModelScope.launch {
                    try {
                        if(_userInputText != UserInputType.ANY) {
                            resultsUseCases.getResults(_userInputText).forEach {
                                if(!_searchResultState.value.results.contains(it))
                                    _searchResultState.value = searchResultState.value.copy(
                                        results = searchResultState.value.results + it) }
                        }
                        else {
                            if(!_searchResultState.value.results.contains(
                                    resultsUseCases.getResult(userTextFieldState.value.text.lowercase()))) {
                                _searchResultState.value = searchResultState.value.copy(
                                    results = searchResultState.value.results +
                                            resultsUseCases.getResult(userTextFieldState.value.text)) }
                        }

                        // we need to pass the currentResults to the ResultsViewModel here
                        // before the screen change, we do this by asking the repository
                        // to add to the local variables

                        resultsUseCases.addToLocalResults(_searchResultState.value.results)
                        // now before we switch view models, we need to clear up the data stored
                        // in the _searchResultState value, otherwise we'll add duplicates

                        _searchResultState.value = searchResultState.value.copy(
                            results = emptyList()
                        )

                        _eventFlow.emit(UIEvent.GetResult)
                    } catch(e: InvalidResultException){
                        _eventFlow.emit(
                            UIEvent.ShowSnackbar(
                                message = e.message ?: ""
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String): UIEvent()
        object GetResult: UIEvent()
    }
}