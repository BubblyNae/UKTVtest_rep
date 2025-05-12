package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.user_results.ResultsScreen
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.user_search.SearchScreen
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.util.Screen
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.util.Screen.SearchScreen
import com.nathan_jolobelawson.uktv_devtest.ui.theme.UKTV_DevTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UKTV_DevTestTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ){
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = SearchScreen.route
                    )
                    {
                        composable(route = SearchScreen.route)
                        {
                            SearchScreen(navController = navController)
                        }
                        composable(route = Screen.ResultsScreen.route)
                        {
                            ResultsScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}