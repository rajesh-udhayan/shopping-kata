package com.anonymous.shopping.data.repository

import com.anonymous.shopping.data.model.Product
import com.anonymous.shopping.data.repository.datasource.ProductLocalDataSource
import com.anonymous.shopping.data.repository.datasource.ProductRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductsRepository @Inject constructor(private val productLocalDataSource: ProductLocalDataSource,
                                             private val productRemoteDataSource: ProductRemoteDataSource
){

    suspend fun getProducts(): Flow<List<Product>> {
        return getProductsFromDB()
    }

    private suspend fun getProductsFromDB(): Flow<List<Product>> {
        lateinit var productList: Flow<List<Product>>
        try {
            productList = productLocalDataSource.getProductsFromDB()
        } catch (e: Exception){
            e.printStackTrace()
        }

        if (productList.first().isNotEmpty()){
            return productList
        } else {
            productList = flow {
                emit(getProductsFromApi())
            }
            productLocalDataSource.saveProductsToDB(getProductsFromApi())
        }
        return productList
    }

    private suspend fun getProductsFromApi(): List<Product> {
         var productList: List<Product> = listOf()
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

    suspend fun updateFavorite(isFavorite: Int, id: String){
        productLocalDataSource.updateFavorite(isFavorite,id)
    }

    fun getFavoriteProducts(): Flow<List<Product>>{
        return productLocalDataSource.getFavoriteProductsFromDB()
    }

    fun getProductDetails(id: String): Flow<Product>{
        return productLocalDataSource.getProductDetailsFromDB(id)
    }
}