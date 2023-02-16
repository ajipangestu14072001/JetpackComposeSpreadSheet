package com.example.spreadsheetjetpackcompose.view.component.screen.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spreadsheetjetpackcompose.model.Destination

@ExperimentalFoundationApi
@Composable
fun CourseSection(destination: List<Destination>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Recommendation",
            modifier = Modifier.padding(5.dp),
            fontSize = 16.sp
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(horizontal = 5.dp),
        ) {
            items(destination) {
                DestinationItem(destination = it)
            }
        }
    }
}