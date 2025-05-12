package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.user_search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.user_results.components.TransparentHintTextField
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val userTextFieldState = viewModel.userTextFieldState.value
    val keyboardController = LocalSoftwareKeyboardController.current
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is SearchViewModel.UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is SearchViewModel.UIEvent.GetResult -> {
                    // here we switch screens, the search view model calls the useCase
                    // the useCase then calls the repository and handles the error logic
                    navController.navigate(Screen.ResultsScreen.route)
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        paddingValue ->
        Box(
            modifier = Modifier
            .fillMaxSize()
            .background(
                Color(
                    ColorUtils.blendARGB(
                        Color.Blue.value.toInt(), Color.LightGray.value.toInt(), 0.2f)))
            .padding(paddingValue),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                TransparentHintTextField(
                    text = userTextFieldState.text,
                    hint = userTextFieldState.hint,
                    onValueChange = {
                        viewModel.onEvent(SearchResultEvent.OnInputInSearchField(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(SearchResultEvent.OnChangeSearchFieldFocus(it))
                    },
                    isHintVisible = userTextFieldState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        viewModel.onEvent(SearchResultEvent.OnSearchButtonPressed)
                        keyboardController?.hide()}
                    )
                {
                    Text("Search")
                }
            }
        }
    }
}
