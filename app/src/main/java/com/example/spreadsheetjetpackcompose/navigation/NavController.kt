package com.example.spreadsheetjetpackcompose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.spreadsheetjetpackcompose.view.component.screen.all.AllDataScreen
import com.example.spreadsheetjetpackcompose.view.component.screen.auth.AuthScreen
import com.example.spreadsheetjetpackcompose.view.component.screen.dashboard.DashBoardScreen
import com.example.spreadsheetjetpackcompose.view.component.screen.edit.EditScreen
import com.example.spreadsheetjetpackcompose.view.component.screen.main.MainScreen
import com.example.spreadsheetjetpackcompose.view.component.screen.profile.DetailProfile
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun NavController(
    navController: NavHostController,
    startDestination: String
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(route = Screen.AllData.route) {
            AllDataScreen(navController = navController)
        }
        composable(route = Screen.Main.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.Edit.route) {
            EditScreen(navController = navController)
        }

        composable(route = Screen.DashBoard.route) {
            DashBoardScreen(navController = navController)
        }

        composable(route = Screen.Auth.route) {
            AuthScreen(navController = navController)
        }

        composable(route = Screen.Profile.route) {
            DetailProfile(navController = navController)
        }
    }

}