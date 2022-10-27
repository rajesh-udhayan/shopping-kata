package com.anonymous.shopping.presentation.beverage_list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.anonymous.shopping.presentation.MainViewModel
import com.anonymous.shopping.presentation.navigation.BottomNavigation
import com.anonymous.shopping.presentation.navigation.NavigationGraph

@Composable
fun MainScreenView(viewModel: MainViewModel){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        NavigationGraph(navController = navController, viewModel)
    }
}