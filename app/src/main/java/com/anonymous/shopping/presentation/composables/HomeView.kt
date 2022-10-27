package com.anonymous.shopping.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.anonymous.shopping.presentation.MainViewModel

@Composable
fun HomeView(navController: NavController, viewModel: MainViewModel) {
    val productList by viewModel.products.observeAsState()
    viewModel.getProducts()
    ProductListView(navController = navController, viewModel = viewModel, productList = productList)
}
