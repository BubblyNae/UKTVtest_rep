package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.user_results

import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.IResultData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.util.OrderType
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.util.ResultOrder

data class ResultsState(
    val results: List<IResultData> = emptyList(),
    val resultOrder: ResultOrder = ResultOrder.Reference(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
