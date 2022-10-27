package com.anonymous.shopping.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anonymous.shopping.data.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun saveProducts(product: List<Product>)

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

    @Query("SELECT * FROM products")
    fun getProducts(): Flow<List<Product>>

    @Query("UPDATE products SET isFavorite = :isFavorite WHERE id LIKE :id")
    suspend fun updateFavorite(isFavorite: Int, id: String)

    @Query("SELECT * FROM products where isFavorite = 1")
    fun getFavoriteProducts(): Flow<List<Product>>

    @Query("SELECT * FROM products where id LIKE :id")
    fun getProductDetails(id: String): Flow<Product>

}
