package com.anonymous.shopping.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.anonymous.shopping.presentation.beverage_list.MainScreenView
import com.anonymous.shopping.presentation.beverage_list.ProductListView
import com.anonymous.shopping.presentation.theme.ShoppingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingTheme {
                MainScreenView(viewModel)
            }
        }
    }
}