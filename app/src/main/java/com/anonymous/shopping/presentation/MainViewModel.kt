package com.anonymous.shopping.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anonymous.shopping.data.model.Product
import com.anonymous.shopping.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ProductsRepository): ViewModel() {

    var isLoaded = mutableStateOf(false)

    private val _products: MutableLiveData<List<Product>> = MutableLiveData()
    val products: LiveData<List<Product>> = _products

    private val _favoriteProducts: MutableLiveData<List<Product>> = MutableLiveData()
    val favoriteProducts: LiveData<List<Product>> = _favoriteProducts

    private val _productDetail: MutableLiveData<Product> = MutableLiveData()
    val productDetail: LiveData<Product> = _productDetail

     fun getProducts() = viewModelScope.launch {
         repository.getProducts().collect { values ->
             _products.value = values
             isLoaded.value = true
         }
     }

    fun getFavoriteProducts() = viewModelScope.launch {
        repository.getFavoriteProducts().collect() { values ->
            _favoriteProducts.value = values
            isLoaded.value = true
        }
    }

    fun updateFavorite(isFavorite: Int, id: String){
        viewModelScope.launch {
            repository.updateFavorite(isFavorite,id)
        }
    }

    fun getProductDetail(id: String) = viewModelScope.launch {
        repository.getProductDetails(id).collect() { values ->
            _productDetail.value = values
        }
    }

}