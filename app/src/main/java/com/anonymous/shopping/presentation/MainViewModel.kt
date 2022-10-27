package com.anonymous.shopping.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.anonymous.shopping.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ProductsRepository): ViewModel() {

    var isLoaded = mutableStateOf(false)

    val productList = liveData {
        emit(repository.getProducts())
        isLoaded.value = true
    }

    fun updateFavorite(isFavorite: Int, id: String){
        viewModelScope.launch {
            repository.updateFavorite(isFavorite,id)
        }
    }

}