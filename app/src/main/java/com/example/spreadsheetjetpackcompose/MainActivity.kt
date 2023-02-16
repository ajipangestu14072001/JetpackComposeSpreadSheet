package com.example.spreadsheetjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import com.example.spreadsheetjetpackcompose.navigation.NavController
import com.example.spreadsheetjetpackcompose.navigation.Screen
import com.example.spreadsheetjetpackcompose.ui.theme.SpreadsheetJetpackComposeTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpreadsheetJetpackComposeTheme {
                    val navController = rememberAnimatedNavController()
                    NavController(
                        navController = navController,
                        startDestination = Screen.DashBoard.route
                    )
            }
        }
    }
}
