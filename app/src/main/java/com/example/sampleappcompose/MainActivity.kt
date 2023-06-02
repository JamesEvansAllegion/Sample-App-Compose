package com.example.sampleappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sampleappcompose.ui.components.BottomNavigationBar
import com.example.sampleappcompose.ui.screens.Screens
import com.example.sampleappcompose.ui.components.TopBar
import com.example.sampleappcompose.ui.screens.HomeScreen
import com.example.sampleappcompose.ui.screens.InfoScreen
import com.example.sampleappcompose.ui.screens.SearchScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val displayLogs = remember { mutableStateOf(false) }
    Scaffold(
        topBar = { TopBar(navController,displayLogs) },
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding -> Box(modifier = Modifier.padding(padding)) {
                Navigation(navController = navController, displayLogs)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}


@Composable
fun Navigation(navController: NavHostController, displayLogs: MutableState<Boolean>) {
    NavHost(navController, startDestination = Screens.Home.route) {
        composable(Screens.Home.route) {
            HomeScreen()
        }
        composable(Screens.Search.route) {
            SearchScreen(displayLogs)
        }
        composable(Screens.Info.route) {
            InfoScreen()
        }
    }
}














