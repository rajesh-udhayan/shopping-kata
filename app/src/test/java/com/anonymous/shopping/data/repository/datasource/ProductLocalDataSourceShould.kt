package com.anonymous.shopping.data.repository.datasource

import com.anonymous.shopping.data.db.ProductDao
import com.anonymous.shopping.data.repository.ProductsRepository
import com.anonymous.shopping.utils.BaseUnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
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
}