package com.example.sampleappcompose

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object Info : Screen("info")
}
