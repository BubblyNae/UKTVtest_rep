package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.util

sealed class ResultOrder(val orderType: OrderType) {
    class Reference(orderType: OrderType): ResultOrder(orderType)

    fun copy(orderType: OrderType): ResultOrder {
        return when(this){
            is Reference -> Reference(orderType)
        }
    }
}