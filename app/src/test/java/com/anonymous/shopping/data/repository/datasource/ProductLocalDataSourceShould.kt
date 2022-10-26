package com.anonymous.shopping.data.repository.datasource

import com.anonymous.shopping.data.db.ProductDao
import com.anonymous.shopping.data.model.Product
import com.anonymous.shopping.data.repository.ProductsRepository
import com.anonymous.shopping.utils.BaseUnitTest
import io.mockk.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ProductLocalDataSourceShould: BaseUnitTest() {

    @Test
    fun getProductsFromLocalDataSource() = runTest {
        val productDao = mockk<ProductDao>()
        val productLocalDataSource = ProductLocalDataSource(productDao)

        coEvery { productDao.getProducts() } returns flow{
            emit(mockk())
        }
        productLocalDataSource.getProductsFromDB()

        coVerify { productDao.getProducts() }
    }

    @Test
    fun saveProductsTomLocalDataSource() = runTest {
        val productDao = mockk<ProductDao>()
        val productLocalDataSource = ProductLocalDataSource(productDao)

        coEvery { productDao.saveProducts(listOf()) } just runs
        productLocalDataSource.saveProductsToDB(listOf())

        coVerify { productDao.saveProducts(listOf()) }
    }

    @Test
    fun deleteProductsFromLocalDataSource() = runTest {
        val productDao = mockk<ProductDao>()
        val productLocalDataSource = ProductLocalDataSource(productDao)

        coEvery { productDao.deleteAllProducts() } just runs
        productLocalDataSource.clearAll()

        coVerify { productDao.deleteAllProducts() }
    }

    @Test
    fun updateProductFromLocalDataSource() = runTest {
        val productDao = mockk<ProductDao>()
        val productLocalDataSource = ProductLocalDataSource(productDao)

        val isFavorite = 1
        val id = "1234"
        coEvery { productDao.updateFavorite(isFavorite,id) } just runs
        productLocalDataSource.updateFavorite(isFavorite,id)

        coVerify { productDao.updateFavorite(isFavorite,id) }
    }
}