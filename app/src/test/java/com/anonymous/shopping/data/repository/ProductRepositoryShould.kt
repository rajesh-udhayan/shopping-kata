package com.anonymous.shopping.data.repository

import com.anonymous.shopping.data.model.Product
import com.anonymous.shopping.data.repository.datasource.ProductLocalDataSource
import com.anonymous.shopping.utils.BaseUnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ProductRepositoryShould: BaseUnitTest() {

    @Test
    fun getProductsFromLocalDataSource() = runTest {
        val productLocalDataSource = mockk<ProductLocalDataSource>()
        val repository = ProductsRepository(productLocalDataSource)

        coEvery { productLocalDataSource.getProductsFromDB() } returns flow{
            emit(mockk())
        }
        repository.getProducts()

        coVerify { productLocalDataSource.getProductsFromDB() }
    }
}