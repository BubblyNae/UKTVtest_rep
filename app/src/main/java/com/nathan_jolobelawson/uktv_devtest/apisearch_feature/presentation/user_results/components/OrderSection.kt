package com.nathan_jolobelawson.uktv_devtest.apisearch_feature.presentation.user_results.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.util.OrderType
import com.nathan_jolobelawson.uktv_devtest.apisearch_feature.domain.util.ResultOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    resultOrder: ResultOrder = ResultOrder.Reference(OrderType.Descending),
    onOrderChange: (ResultOrder) -> Unit
)
{
    Column(
        modifier = modifier
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            DefaultRadioButton(
                text = "Reference",
                selected = resultOrder is ResultOrder.Reference,
                onSelect = { onOrderChange(ResultOrder.Reference(resultOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        )
        {
            DefaultRadioButton(
                text = "Ascending",
                selected = resultOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(resultOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = resultOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(resultOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}