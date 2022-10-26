package com.anonymous.shopping.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anonymous.shopping.data.model.Product
import com.anonymous.shopping.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ProductsRepository): ViewModel() {

    val productList = MutableLiveData<Result<List<Product>>>()

    init {
        repository.getProducts()
    }
}