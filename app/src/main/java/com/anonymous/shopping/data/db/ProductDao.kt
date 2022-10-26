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

}
