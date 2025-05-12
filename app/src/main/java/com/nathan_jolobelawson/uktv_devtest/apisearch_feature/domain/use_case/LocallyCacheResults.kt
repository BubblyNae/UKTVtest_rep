package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.use_case

import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.FilmsResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.ShipsResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.VehicleResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.repository.ResultRepository

class LocallyCacheResults(private val repository: ResultRepository) {
    operator fun invoke(
        filmResultsData: List<FilmsResultsData> = emptyList(),
        shipsResultsData: List<ShipsResultsData> = emptyList(),
        vehicleResultsData: List<VehicleResultsData> = emptyList()) {
        repository.LocallyCacheResults(
            filmsResultsData = filmResultsData,
            shipsResultsData = shipsResultsData,
            vehicleResultsData = vehicleResultsData)
    }

}