package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.util

sealed class Screen (val route: String) {
    object SearchScreen: Screen("search_screen")
    object ResultsScreen: Screen("results_screen")
}