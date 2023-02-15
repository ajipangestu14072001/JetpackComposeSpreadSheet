package com.example.spreadsheetjetpackcompose.navigation

sealed class Screen (val route: String){
    object AllData : Screen(route = "allData")
    object Main : Screen(route = "main")
}