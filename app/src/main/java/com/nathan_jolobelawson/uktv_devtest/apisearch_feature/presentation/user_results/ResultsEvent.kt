package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.user_results

import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.util.ResultOrder

sealed class ResultsEvent {
    data class Order(val resultOrder: ResultOrder): ResultsEvent()
    object ClearResults: ResultsEvent()
    object ToggleOrderSection: ResultsEvent()
    object ReturnToSearch: ResultsEvent()
}