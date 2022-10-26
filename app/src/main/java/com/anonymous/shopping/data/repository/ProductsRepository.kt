package com.anonymous.shopping.data.repository

import android.util.Log
import com.anonymous.shopping.data.model.Product
import com.anonymous.shopping.data.repository.datasource.ProductLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val productLocalDataSource: ProductLocalDataSource){

    suspend fun getProducts(): Flow<List<Product>> {
        return getMoviesFromDB()
    }

    suspend fun getMoviesFromDB(): Flow<List<Product>> {
        lateinit var productList: Flow<List<Product>>
        try {
            productList = productLocalDataSource.getProductsFromDB()
        } catch (e: Exception){

        }
        if (productList.count() != 0){
            return productList
        } else {
           //TODO get data from api
        }
        return productList
    }
}