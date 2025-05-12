package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}