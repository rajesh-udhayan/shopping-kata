package com.anonymous.shopping.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.anonymous.shopping.presentation.MainViewModel

@Composable
fun FavoriteView(navController: NavController, viewModel: MainViewModel){
    val favoriteProducts by viewModel.favoriteProducts.observeAsState()
    viewModel.getFavoriteProducts()
    ProductListView(navController = navController, viewModel = viewModel, productList = favoriteProducts)
}