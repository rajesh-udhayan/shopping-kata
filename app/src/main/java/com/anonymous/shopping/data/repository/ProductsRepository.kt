package com.anonymous.shopping.data.repository

import com.anonymous.shopping.data.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRepository @Inject constructor(){
    fun getProducts(): Flow<Result<List<Product>>> {
        TODO("Not yet implemented")
    }
}