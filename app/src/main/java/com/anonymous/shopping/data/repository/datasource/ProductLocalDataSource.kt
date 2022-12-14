package com.anonymous.shopping.data.repository.datasource

import com.anonymous.shopping.data.db.ProductDao
import com.anonymous.shopping.data.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductLocalDataSource @Inject constructor(private val productDao: ProductDao) {

    fun getProductsFromDB(): Flow<List<Product>> {
        return productDao.getProducts()
    }

    suspend fun saveProductsToDB(products: List<Product>) {
        CoroutineScope(Dispatchers.IO).launch {
            productDao.saveProducts(products)
        }
    }

    suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            productDao.deleteAllProducts()
        }
    }

    suspend fun updateFavorite(isFavorite: Int, id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            productDao.updateFavorite(isFavorite, id)
        }
    }

    fun getFavoriteProductsFromDB():Flow<List<Product>> {
        return productDao.getFavoriteProducts()
    }

    fun getProductDetailsFromDB(id: String): Flow<Product> {
        return productDao.getProductDetails(id)
    }

}
