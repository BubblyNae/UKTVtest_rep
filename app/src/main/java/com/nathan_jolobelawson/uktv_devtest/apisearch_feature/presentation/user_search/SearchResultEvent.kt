package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.user_search

import androidx.compose.ui.focus.FocusState

sealed class SearchResultEvent {
    data class OnInputInSearchField(val value: String): SearchResultEvent()
    data class OnChangeSearchFieldFocus(val focusState: FocusState): SearchResultEvent()

    object OnSearchButtonPressed: SearchResultEvent()
}