package com.example.spreadsheetjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.spreadsheetjetpackcompose.navigation.NavController
import com.example.spreadsheetjetpackcompose.navigation.Screen
import com.example.spreadsheetjetpackcompose.ui.theme.SpreadsheetJetpackComposeTheme
import com.example.spreadsheetjetpackcompose.view.main.screen.MainScreen
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpreadsheetJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberAnimatedNavController()
                    NavController(
                        navController = navController,
                        startDestination = Screen.Main.route
                    )
                }
            }
        }
    }
}
