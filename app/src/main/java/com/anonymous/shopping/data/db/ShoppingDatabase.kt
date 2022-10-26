package com.anonymous.shopping.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anonymous.shopping.data.model.Product

@Database(entities = [Product::class],
version = 1,
exportSchema = false)
abstract class ShoppingDatabase : RoomDatabase(){
abstract fun productDao(): ProductDao
}