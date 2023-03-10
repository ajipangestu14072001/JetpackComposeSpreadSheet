package com.example.spreadsheetjetpackcompose.navigation

sealed class Screen (val route: String){
    object AllData : Screen(route = "allData")
    object Main : Screen(route = "main")
    object Edit : Screen(route = "edit")
    object DashBoard : Screen(route = "dashboard")
    object Auth : Screen(route = "auth")
    object Profile : Screen(route = "profile")
}