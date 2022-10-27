package com.anonymous.shopping.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anonymous.shopping.presentation.MainViewModel
import com.anonymous.shopping.presentation.navigation.BottomNavigation
import com.anonymous.shopping.presentation.navigation.NavigationGraph

@Composable
fun MainScreenView(viewModel: MainViewModel) {
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

        val navController = rememberNavController()

        val navBackStackEntry by navController.currentBackStackEntryAsState()

        when (navBackStackEntry?.destination?.route) {
            "home" -> {
                bottomBarState.value = true
            }
            "favorite" -> {
                bottomBarState.value = true
            }
            else -> {
                bottomBarState.value = false
            }
        }

        Scaffold(
            bottomBar = {
                BottomNavigation(
                    navController = navController,
                    bottomBarState = bottomBarState
                )
            },
            content = {
                Column(modifier = Modifier.padding(bottom = 58.dp)) {
                    NavigationGraph(navController = navController, viewModel)
                }
            }
        )
}