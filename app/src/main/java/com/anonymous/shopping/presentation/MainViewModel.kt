package com.anonymous.shopping.presentation

import androidx.lifecycle.*
import com.anonymous.shopping.data.model.Product
import com.anonymous.shopping.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ProductsRepository): ViewModel() {

    val productList = liveData {
        emit(repository.getProducts())
    }

}