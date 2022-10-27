package com.anonymous.shopping.presentation.beverage_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.anonymous.shopping.presentation.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun HomeView(navController: NavController, viewModel: MainViewModel) {
    val productList by viewModel.products.observeAsState()
    viewModel.getProducts()
    ProductListView(navController = navController, viewModel = viewModel, productList = productList)
}
