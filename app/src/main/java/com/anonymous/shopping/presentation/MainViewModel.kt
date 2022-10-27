package com.anonymous.shopping.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.anonymous.shopping.data.model.Product
import com.anonymous.shopping.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ProductsRepository): ViewModel() {

    var isLoaded = mutableStateOf(false)

    private val _products: MutableLiveData<List<Product>> = MutableLiveData()
    val products: LiveData<List<Product>> = _products

    private val _favoriteProducts: MutableLiveData<List<Product>> = MutableLiveData()
    val favoriteProducts: LiveData<List<Product>> = _favoriteProducts

     fun getProducts() = viewModelScope.launch {
        repository.getProducts().collect { values ->
            _products.value = values
        }
         isLoaded.value = true
     }

    fun getFavoriteProducts() = viewModelScope.launch {
        repository.getFavoriteProducts().collect() { values ->
            _favoriteProducts.value = values
        }
        isLoaded.value = true
    }

    fun updateFavorite(isFavorite: Int, id: String){
        viewModelScope.launch {
            repository.updateFavorite(isFavorite,id)
        }
    }

}