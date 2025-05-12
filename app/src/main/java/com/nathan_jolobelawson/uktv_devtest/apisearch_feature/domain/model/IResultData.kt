package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model

interface IResultData {
    val url: String
    val timestamp: Long
}

class InvalidResultException(message: String): Exception(message)