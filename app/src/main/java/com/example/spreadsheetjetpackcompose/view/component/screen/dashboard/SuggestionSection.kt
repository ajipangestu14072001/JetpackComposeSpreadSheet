package com.example.spreadsheetjetpackcompose.view.component.screen.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.spreadsheetjetpackcompose.R
import com.example.spreadsheetjetpackcompose.navigation.Screen
import com.example.spreadsheetjetpackcompose.ui.theme.TextWhite

@Composable
fun SuggestionSection(
    navController: NavHostController
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(color = 0xFFFF74B1))
            .padding(horizontal = 15.dp, vertical = 15.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.AllData.route)
            }
    ) {
        Column {
            Text(
                text = "Semua Wisata",
            )
            Text(
                text = "Wonderful Indonesia",
                color = TextWhite
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(color = 0xFFFF74B1))
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}