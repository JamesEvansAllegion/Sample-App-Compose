package com.example.sampleappcompose.ui.screens

import com.example.sampleappcompose.R

sealed class Screens(var route: String, var icon: Int, var title: String) {
    object Home : Screens("home", R.drawable.ic_home, "Home")
    object Search : Screens("search", R.drawable.ic_search, "Search")
    object Info : Screens("info", R.drawable.ic_info, "Info")
}
