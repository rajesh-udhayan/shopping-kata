package com.anonymous.shopping.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.anonymous.shopping.presentation.MainViewModel
import com.anonymous.shopping.presentation.composables.FavoriteView
import com.anonymous.shopping.presentation.composables.HomeView
import com.anonymous.shopping.presentation.composables.ProductDetailView

@Composable
fun NavigationGraph(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController, startDestination = Screen.Home.screen_route){
        composable(Screen.Home.screen_route){
            HomeView(navController,viewModel)
        }
        composable(Screen.Favorite.screen_route){
            FavoriteView(navController,viewModel)
        }
        composable(route = Screen.ProductDetail.screen_route + "/{id}",
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
            }
        )){ entry ->
            ProductDetailView(id = entry.arguments?.getString("id"),
                navController,
                viewModel)
        }
    }
}