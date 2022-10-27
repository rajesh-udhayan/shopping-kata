package com.anonymous.shopping.data.repository

import android.util.Log
import com.anonymous.shopping.data.model.Product
import com.anonymous.shopping.data.repository.datasource.ProductLocalDataSource
import com.anonymous.shopping.data.repository.datasource.ProductRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val productLocalDataSource: ProductLocalDataSource,
                                             private val productRemoteDataSource: ProductRemoteDataSource
){

    suspend fun getProducts(): List<Product> {
        return getMoviesFromDB()
    }

    suspend fun getMoviesFromDB(): List<Product> {
        lateinit var productList: List<Product>
        try {
            productList = productLocalDataSource.getProductsFromDB()
        } catch (e: Exception){
            e.printStackTrace()
        }

        if (productList.size != 0){
            return productList
        } else {
            productList = getProductsFromApi()
            productLocalDataSource.saveProductsToDB(productList)
        }
        return productList
    }

    suspend fun getProductsFromApi(): List<Product> {
        lateinit var productList: List<Product>
        try {
            val response = productRemoteDataSource.getProducts()
            val body = response.body()
            if(body != null){
                productList = body.products
            }
        } catch (e: Exception){
            e.printStackTrace()
        }
        return productList
    }

}