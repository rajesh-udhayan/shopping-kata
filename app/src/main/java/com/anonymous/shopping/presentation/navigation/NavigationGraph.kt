package com.anonymous.shopping.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anonymous.shopping.presentation.MainViewModel
import com.anonymous.shopping.presentation.beverage_list.FavoriteView
import com.anonymous.shopping.presentation.beverage_list.HomeView
import com.anonymous.shopping.presentation.beverage_list.ProductListView

@Composable
fun NavigationGraph(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route){
        composable(BottomNavItem.Home.screen_route){
            HomeView(viewModel)
        }
        composable(BottomNavItem.Favorite.screen_route){
            FavoriteView(viewModel)
        }
    }
}