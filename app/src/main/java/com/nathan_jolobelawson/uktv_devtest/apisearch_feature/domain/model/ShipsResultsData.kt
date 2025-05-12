package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model

import androidx.room.Entity

// this is the data class for every ship entry recovered from the ship api call.
// we inherit from IResultData to gain access to composable methods that can be called on
// each entry in the ResultsValue without resorting to when statements (which aren't a
// problem efficiency-wise but could make the feature harder to refactor)

@Entity
data class ShipsResultsData(
    override val url: String,
    override val timestamp: Long,
    val name: String,

    val model: String,
    val manufacturer: String,
    val cost_in_credits: String, // probably could do a conversion to an int value type here
    val length: String,

    // due to ships having higher capacities, this needs to be a string unlike the VehicleData
    val crew: String,

    val passengers: String,
    val cargo_capacity: String,

    ) : IResultData
