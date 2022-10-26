package com.anonymous.shopping.data.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.anonymous.shopping.data.model.Product
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
@SmallTest
class ProductDaoTest {

    private lateinit var database: ShoppingDatabase
    private lateinit var productsDao: ProductDao

    @Before
    fun setUpDatabase(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingDatabase::class.java
        ).allowMainThreadQueries().build()

        productsDao = database.productDao()
    }

    @After
    fun closeDatabase(){
        database.close()
    }

    @Test
    fun insertAndRetrieveProducts() = runBlocking {
        val product = mockProductData()

        productsDao.saveProducts(listOf(product))

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            productsDao.getProducts().collect {
                assertThat(it).contains(product)
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun deleteAllProducts() = runBlocking {
        val product1 = mockProductData()
        val product2 = mockProductData()

        productsDao.saveProducts(listOf(product1, product2))
        productsDao.deleteAllProducts()

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            productsDao.getProducts().collect {
                assertThat(it).doesNotContain(product1)
                assertThat(it).doesNotContain(product2)
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()
    }

    private fun mockProductData(): Product {
        val product = Product(
            "23124",
            "https://media.danmurphys.com.au/dmo/product/23124-1.png?impolicy=PROD_SM",
            10.18,
            4.0,
            "Diamond Label Shiraz",
            11,
            0
        )
        return product
    }

}