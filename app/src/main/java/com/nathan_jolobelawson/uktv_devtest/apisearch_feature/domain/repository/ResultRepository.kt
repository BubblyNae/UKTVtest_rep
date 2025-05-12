package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.repository

import androidx.compose.runtime.MutableState
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.FilmsResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.IResultData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.ShipsResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.VehicleResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.util.UserInputType

// while this is a bit much for cached data, setting this up now will allow for easier
// expansion later

interface ResultRepository {

    fun LocallyCacheResults(
        filmsResultsData: List<FilmsResultsData>,
        shipsResultsData: List<ShipsResultsData>,
        vehicleResultsData: List<VehicleResultsData>
        )

    // this gets the results from the locally stored variables below
    // it will grab one of the lists depending on UserInputType
    fun getResults(userInputType: UserInputType): List<IResultData>

    // this searches through the locally stored variables below
    // to see if any of the lists match the user's input
    suspend fun getResult(userInput: String): IResultData

    fun clearLocalResults()
    fun addToLocalResults(resultsToAdd: List<IResultData>)
    fun isResultsCached(): Boolean
    fun getLocalResults(): List<IResultData>

    // this stores all the data the user has searched so far
    var _allResultsData: MutableState<List<IResultData>>

    var _filmsResultsData: MutableState<List<FilmsResultsData>>
    var _shipsResultsData: MutableState<List<ShipsResultsData>>
    var _vehicleResultsData: MutableState<List<VehicleResultsData>>
}