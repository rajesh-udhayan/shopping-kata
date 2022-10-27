package com.anonymous.shopping.presentation.beverage_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.anonymous.shopping.presentation.MainViewModel

@Composable
fun FavoriteView(viewModel: MainViewModel){
    val favoriteProducts by viewModel.favoriteProducts.observeAsState()
    viewModel.getFavoriteProducts()
    ProductListView(viewModel = viewModel, productList = favoriteProducts)
}