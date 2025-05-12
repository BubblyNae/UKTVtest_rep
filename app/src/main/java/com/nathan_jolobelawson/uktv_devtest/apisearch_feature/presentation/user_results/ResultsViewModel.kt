package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.user_results

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.FilmsResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.ShipsResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.VehicleResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.use_case.ResultsUseCases
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.util.OrderType
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.util.ResultOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val resultsUseCases: ResultsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(ResultsState())
    val state: State<ResultsState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        _state.value = state.value.copy(
            results = resultsUseCases.getLocalResults()
        )

        getOrderedResults(_state.value.resultOrder)
    }

    fun onEvent(event: ResultsEvent){
        when(event) {
            is ResultsEvent.Order -> {
                if(state.value.resultOrder::class == event.resultOrder::class &&
                    state.value.resultOrder.orderType == event.resultOrder.orderType)
                {
                    return
                }

                getOrderedResults(event.resultOrder)

            }
            is ResultsEvent.ClearResults -> {
                // this will probably need more work
                _state.value = state.value.copy(results = emptyList())
                resultsUseCases.clearLocalResults()

                viewModelScope.launch {
                    try {
                        _eventFlow.emit(UIEvent.ClearResults)
                    }
                    catch(e: IllegalArgumentException) {
                        Log.e("IllegalArgumentException", "Error Clearing Results")
                    }
                }
            }
            is ResultsEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
            is ResultsEvent.ReturnToSearch -> {
                viewModelScope.launch {
                    try {
                        _eventFlow.emit(UIEvent.ReturnToSearch)
                    }
                    catch(e: IllegalArgumentException) {
                        Log.e("IllegalArgumentException", "Error Returning to Search Screen")
                    }
                }
            }
        }
    }

    @Throws(IllegalArgumentException::class)
    private fun getOrderedResults(resultOrder: ResultOrder) {
        _state.value = state.value.copy(
            resultOrder = resultOrder,
            results =
                when(resultOrder.orderType)
                {
                    is OrderType.Ascending -> {
                        when(resultOrder) {
                            is ResultOrder.Reference -> state.value.results.sortedBy {
                                when(it)
                                {
                                    is FilmsResultsData ->
                                        it.title.lowercase()
                                    is ShipsResultsData ->
                                        it.name.lowercase()
                                    is VehicleResultsData ->
                                        it.name.lowercase()
                                    else -> throw
                                    IllegalArgumentException("Contains a Result without a type! ")
                                }
                            }
                        }
                    }
                    is OrderType.Descending -> {
                        when(resultOrder) {
                            is ResultOrder.Reference -> state.value.results.sortedByDescending {
                                when(it)
                                {
                                    is FilmsResultsData ->
                                        it.title.lowercase()
                                    is ShipsResultsData ->
                                        it.name.lowercase()
                                    is VehicleResultsData ->
                                        it.name.lowercase()
                                    else -> throw
                                    IllegalArgumentException("Contains a Result without a type! ")
                                } }
                        }
                    }
                }
        )
    }

    sealed class UIEvent {
        object ReturnToSearch: UIEvent()
        object ClearResults: UIEvent()
    }
}