package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model

import androidx.room.Entity

// this is the data class for every vehicle entry recovered from the vehicle api call.
// we inherit from IResultData to gain access to composable methods that can be called on
// each entry in the ResultsValue without resorting to when statements (which aren't a
// problem efficiency-wise but could make the feature harder to refactor)

@Entity
data class FilmsResultsData(
    override val url: String,
    override val timestamp: Long,
    val title: String,

    val episode_id: Int,
    val director: String,
    val producer: String,
    val release_date: String,
    val opening_crawl: String
) : IResultData
