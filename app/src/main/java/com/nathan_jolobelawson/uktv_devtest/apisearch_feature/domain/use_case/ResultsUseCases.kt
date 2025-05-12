package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.use_case

data class ResultsUseCases(
    val locallyCacheResults: LocallyCacheResults,
    val getResult: GetResult,
    val getResults: GetResults,
    val clearLocalResults: ClearLocalResults,
    val addToLocalResults: AddToLocalResults,
    val isResultsCached: IsResultsCached,
    val getLocalResults: GetLocalResults
)