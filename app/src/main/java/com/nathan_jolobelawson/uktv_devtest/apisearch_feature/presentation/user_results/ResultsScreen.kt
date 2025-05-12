package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.user_results

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.data.util.SWAPI_CONSTANTS.CLEAR_RESULTS_BUTTON_CONTENT_DESC_TEXT
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.data.util.SWAPI_CONSTANTS.RETURN_TO_SEARCH_BUTTON_CONTENT_DESC_TEXT
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.data.util.SWAPI_CONSTANTS.REVEAL_ORDERING_BUTTON_CONTENT_DESC_TEXT
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.FilmsResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.ShipsResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.VehicleResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.user_results.components.FilmResultItem
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.user_results.components.OrderSection
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.user_results.components.ShipResultItem
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.user_results.components.VehicleResultItem
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ResultsScreen(
    navController: NavController,
    viewModel: ResultsViewModel = hiltViewModel()
)
{
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect (key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is ResultsViewModel.UIEvent.ReturnToSearch -> {
                    navController.navigate(Screen.SearchScreen.route)
                }
                is ResultsViewModel.UIEvent.ClearResults -> {
                    navController.navigate(Screen.SearchScreen.route)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(ResultsEvent.ClearResults)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = CLEAR_RESULTS_BUTTON_CONTENT_DESC_TEXT)
            }
        },
        scaffoldState = scaffoldState,

    ){
        paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(
                    onClick = {
                        viewModel.onEvent(ResultsEvent.ReturnToSearch)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = RETURN_TO_SEARCH_BUTTON_CONTENT_DESC_TEXT
                    )
                }
                Text(
                    text = "Search Results",
                    style = MaterialTheme.typography.h4
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(ResultsEvent.ToggleOrderSection)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = REVEAL_ORDERING_BUTTON_CONTENT_DESC_TEXT
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth(),
                    resultOrder = state.resultOrder,
                    onOrderChange = {
                        viewModel.onEvent(ResultsEvent.Order(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn (
                modifier = Modifier.fillMaxSize()
            ){
                items(state.results) { result ->
                    when(result) {
                        is FilmsResultsData ->
                            FilmResultItem(
                                result = result,
                                modifier = Modifier.fillMaxSize()
                            )
                        is ShipsResultsData ->
                            ShipResultItem(
                                result = result,
                                modifier = Modifier.fillMaxSize()
                            )
                        is VehicleResultsData ->
                            VehicleResultItem(
                                result = result,
                                modifier = Modifier.fillMaxSize()
                            )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}