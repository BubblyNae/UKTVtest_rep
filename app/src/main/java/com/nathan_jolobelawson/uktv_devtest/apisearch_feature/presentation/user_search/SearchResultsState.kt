package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.user_search

import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.IResultData

data class SearchResultsState(
    val results: List<IResultData> = emptyList(),
)
