package com.example.sampleappcompose.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sampleappcompose.R
import com.example.sampleappcompose.ui.screens.Screens

@Composable
fun TopBar(navController: NavController, displayLogs: MutableState<Boolean>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        backgroundColor = Color.White,
        contentColor = Color.Black,
        actions = {
            if (currentRoute == Screens.Search.route) {
                LogButton(displayLogs)
            }
        }
    )
}

//@Preview(showBackground = true)
//@Composable
//fun TopBarPreview() {
//    TopBar()
//}

@Composable
fun LogButton(displayLogs: MutableState<Boolean>) {
    Button(
        onClick = { displayLogs.value = !displayLogs.value }
    ) {
        Text(text = "Display Logs")
    }
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Screens.Home,
        Screens.Search,
        Screens.Info
    )
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { non_null_start_destination_route ->
                            popUpTo(non_null_start_destination_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun BottomNavigationBarPreview() {
//    BottomNavigationBar()
//}
