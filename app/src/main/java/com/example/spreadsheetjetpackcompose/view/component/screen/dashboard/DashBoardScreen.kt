package com.example.spreadsheetjetpackcompose.view.component.screen.dashboard

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.spreadsheetjetpackcompose.R
import com.example.spreadsheetjetpackcompose.model.Destination
import com.example.spreadsheetjetpackcompose.navigation.Screen
import com.example.spreadsheetjetpackcompose.ui.theme.*
import com.example.spreadsheetjetpackcompose.utils.standardQuadFromTo

@ExperimentalFoundationApi
@Composable
fun DashBoardScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.padding(10.dp)) {
            GreetingSection()
            ChipSection(listOf("Wisata Sejarah", "Wisata Alam", "Wisata Religi", "Wisata Pendidikan"))
            SuggestionSection(navController)
            CourseSection(
                listOf(
                    Destination("Raja Ampat", R.drawable.ic_baseline_explore_24, BlueViolet1, BlueViolet2, BlueViolet3),
                    Destination("Labuan Bajo", R.drawable.ic_baseline_explore_24, LightGreen1, LightGreen2, LightGreen3),
                    Destination("Pantai Kute", R.drawable.ic_baseline_explore_24, skyblue1, skyblue2, skyblue3),
                    Destination("Gunung Lawu", R.drawable.ic_baseline_explore_24, Beige1, Beige2, Beige3),
                    Destination("Gunung Bromo", R.drawable.ic_baseline_explore_24, OrangeYellow1, OrangeYellow2, OrangeYellow3),
                    Destination("Hutan Monyet", R.drawable.ic_baseline_explore_24, LightGreen1, LightGreen2, LightGreen3),
                )
            )
        }
    }
}











