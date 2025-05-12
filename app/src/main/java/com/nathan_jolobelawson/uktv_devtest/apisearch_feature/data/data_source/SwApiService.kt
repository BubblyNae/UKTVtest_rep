package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.data.data_source

import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.data.util.SWAPI_CONSTANTS
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.FilmsResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.ShipsResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.VehicleResultsData
import retrofit2.http.GET

interface SwApiService {
    @GET(SWAPI_CONSTANTS.STAR_WARS_STARSHIPS_RELATIVE_URL)
    suspend fun getShipResults(): List<ShipsResultsData>

    @GET(SWAPI_CONSTANTS.STAR_WARS_FILMS_RELATIVE_URL)
    suspend fun getFilmResults(): List<FilmsResultsData>

    @GET(SWAPI_CONSTANTS.STAR_WARS_VEHICLE_RELATIVE_URL)
    suspend fun getVehicleResults(): List<VehicleResultsData>
}