package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.data.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.data.util.SWAPI_CONSTANTS
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.FilmsResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.IResultData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.InvalidResultException
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.ShipsResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.VehicleResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.repository.ResultRepository
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.util.UserInputType

class ResultRepositoryImpl(): ResultRepository {

    // this method is called when the app opens and populates the variables cached locally
    // in this class to minimize API calls

    override fun LocallyCacheResults(
        filmsResultsData: List<FilmsResultsData>,
        shipsResultsData: List<ShipsResultsData>,
        vehicleResultsData: List<VehicleResultsData>
    ) {
        if(filmsResultsData.isNotEmpty())
            _filmsResultsData.value = filmsResultsData
        if(shipsResultsData.isNotEmpty())
            _shipsResultsData.value = shipsResultsData
        if(vehicleResultsData.isNotEmpty())
            _vehicleResultsData.value = vehicleResultsData
    }

    override fun getResults(userInputType: UserInputType): List<IResultData> {
        return when(userInputType) {
            UserInputType.VEHICLE -> _vehicleResultsData.value
            UserInputType.FILMS -> _filmsResultsData.value
            UserInputType.STARSHIPS -> _shipsResultsData.value
            else -> emptyList()
        }
    }

    @Throws(InvalidResultException::class)
    override suspend fun getResult(userInput: String): IResultData {

        // this is very gross. with more time I'd definitely look into better model structure
        // or a method of iterating through a classes values.
        // JSON comes to mind first but there might be an easier way?

        _filmsResultsData.value.forEach { result ->
            if( result.title.lowercase() == userInput ||
                result.episode_id == userInput.toIntOrNull() ||
                result.director.lowercase() == userInput ||
                result.producer.lowercase() == userInput ||
                result.release_date.lowercase() == userInput ||
                result.opening_crawl.lowercase() == userInput ) return result
        }

        _shipsResultsData.value.forEach { result ->
            if( result.name.lowercase() == userInput ||
                result.model.lowercase() == userInput ||
                result.manufacturer.lowercase() == userInput ||
                result.cost_in_credits.lowercase() == userInput ||
                result.length.lowercase() == userInput ||
                result.crew.lowercase() == userInput ||
                result.passengers.lowercase() == userInput ||
                result.cargo_capacity.lowercase() == userInput) return result
        }

        _vehicleResultsData.value.forEach { result ->
            if( result.name.lowercase() == userInput ||
                result.model.lowercase() == userInput ||
                result.manufacturer.lowercase() == userInput ||
                result.cost_in_credits.lowercase() == userInput ||
                result.length.lowercase() == userInput ||
                result.crew == userInput.toIntOrNull() ||
                result.passengers.lowercase() == userInput ||
                result.cargo_capacity.lowercase() == userInput) return result
        }

        throw InvalidResultException(SWAPI_CONSTANTS.INVALID_RESULT_EXCEPTION_BAD_SEARCH_MESSAGE)
    }

    override fun clearLocalResults() {
        _allResultsData.value = emptyList()
    }

    override fun addToLocalResults(resultsToAdd: List<IResultData>) {
        _allResultsData.value += resultsToAdd
    }

    override fun isResultsCached(): Boolean {
        return _filmsResultsData.value.isNotEmpty() &&
                _shipsResultsData.value.isNotEmpty() &&
                _vehicleResultsData.value.isNotEmpty()
    }

    override fun getLocalResults(): List<IResultData> {
        return _allResultsData.value
    }

    override var _allResultsData: MutableState<List<IResultData>> =
        mutableStateOf(emptyList())
    override var _filmsResultsData: MutableState<List<FilmsResultsData>> =
        mutableStateOf(emptyList())
    override var _shipsResultsData: MutableState<List<ShipsResultsData>> =
        mutableStateOf(emptyList())
    override var _vehicleResultsData: MutableState<List<VehicleResultsData>> =
        mutableStateOf(emptyList())
}