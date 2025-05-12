package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.user_results.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.FilmsResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.ShipsResultsData
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.model.VehicleResultsData
import com.nathan_jolobelawson.uktv_devtest.ui.theme.Pink40
import com.nathan_jolobelawson.uktv_devtest.ui.theme.Purple40
import com.nathan_jolobelawson.uktv_devtest.ui.theme.PurpleGrey40

@Composable
fun FilmResultItem(
    modifier: Modifier = Modifier,
    result: FilmsResultsData
)
{
    // I really don't like that this code is duplicated for each ResultItem.
    // More time with Compose is needed to see how to do this
    Box( modifier = Modifier ) // layout for ResultItem
    {
        Canvas(modifier = Modifier.matchParentSize())
        {
            drawRoundRect(
                color = Pink40,
                size = size,
                cornerRadius = CornerRadius(10.dp.toPx())
            )
        }
        Column( // the text
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
        {
            Box( modifier = Modifier, contentAlignment = Alignment.Center )
            {
                Text( // the title of the film
                    text = result.title + ": Episode " + result.episode_id,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                // this is used to setup 2 "halves" of the item.
                // One will display details about the film, the other will display the
                // opening scrawl
            )
            {
                Column(
                    // this will contain a list of the details from the film
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )
                {
                    Text( // the director
                        text = "Director: " + result.director,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text( // the producers
                        text = "Producer: " + result.producer,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text( // the release date
                        text = "Release Date: " + result.release_date,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text( // the opening crawl
                    modifier = Modifier.fillMaxWidth().weight(4f),

                    text = "\"" + result.opening_crawl + "\"",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun ShipResultItem(
    modifier: Modifier = Modifier,
    result: ShipsResultsData
)
{
    Box( modifier = Modifier ) // layout for ResultItem
    {
        Canvas(modifier = Modifier.matchParentSize())
        {
            drawRoundRect(
                color = PurpleGrey40,
                size = size,
                cornerRadius = CornerRadius(10.dp.toPx())
            )
        }
        Column( // the text
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
        {
            Box( modifier = Modifier, contentAlignment = Alignment.Center )
            {
                Text( // the name of the starship
                    text = result.name,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                // this will contain a list of the details for the ship
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text( // the model
                    text = "Model: " + result.model,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text( // the Manufacturer
                    text = "Manufacturer: " + result.manufacturer,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text( // the Cost in credits
                    text = "Cost in credits: " + result.cost_in_credits,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text( // the Length
                    text = "Length: " + result.length,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text( // the crew count
                    text = "Crew Count: " + result.crew,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text( // the passenger count
                    text = "Passenger Count: " + result.passengers,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text( // the Cargo Capacity
                    text = "Cargo Capacity: " + result.cargo_capacity,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}

@Composable
fun VehicleResultItem(
    result: VehicleResultsData,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp)
{
    Box( modifier = Modifier ) // layout for ResultItem
    {
        Canvas(modifier = Modifier.matchParentSize())
        {
            drawRoundRect(
                color = Purple40,
                size = size,
                cornerRadius = CornerRadius(10.dp.toPx())
            )
        }
        Column( // the text
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
        {
            Box( modifier = Modifier, contentAlignment = Alignment.Center )
            {
                Text( // the name of the vehicle
                    text = result.name,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                // this will contain a list of the details for the vehicle
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text( // the model
                    text = "Model: " + result.model,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text( // the Manufacturer
                    text = "Manufacturer: " + result.manufacturer,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text( // the Cost in credits
                    text = "Cost in credits: " + result.cost_in_credits,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text( // the Length
                    text = "Length: " + result.length,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text( // the crew count
                    text = "Crew Count: " + result.crew,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text( // the passenger count
                    text = "Passenger Count: " + result.passengers,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text( // the Cargo Capacity
                    text = "Cargo Capacity: " + result.cargo_capacity,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}