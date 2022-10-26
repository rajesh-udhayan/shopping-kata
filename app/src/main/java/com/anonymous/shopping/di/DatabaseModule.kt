package com.anonymous.shopping.di

import android.content.Context
import androidx.room.Room
import com.anonymous.shopping.data.db.ProductDao
import com.anonymous.shopping.data.db.ShoppingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
 class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ShoppingDatabase{
        return Room.databaseBuilder(context,ShoppingDatabase::class.java,"shoppingDB")
            .build()
    }

    @Singleton
    @Provides
    fun provideProductDao(shoppingDatabase: ShoppingDatabase): ProductDao{
        return shoppingDatabase.productDao()
    }
}