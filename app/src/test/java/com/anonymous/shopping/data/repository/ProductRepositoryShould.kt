package com.anonymous.shopping.data.repository

import com.anonymous.shopping.data.model.Product
import com.anonymous.shopping.data.model.ProductList
import com.anonymous.shopping.data.repository.datasource.ProductLocalDataSource
import com.anonymous.shopping.data.repository.datasource.ProductRemoteDataSource
import com.anonymous.shopping.utils.BaseUnitTest
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ProductRepositoryShould: BaseUnitTest() {

    private val productLocalDataSource =  mockk<ProductLocalDataSource>()
    private val productRemoteDataSource = mockk<ProductRemoteDataSource>()
    private lateinit var repository: ProductsRepository

    @Before
    fun setUp(){
        repository = ProductsRepository(productLocalDataSource,productRemoteDataSource)
    }

    @Test
    fun getProductsFromAPIAndSaveToLocalDataSourceWhenDBIsEmpty() = runTest {
        coEvery { productLocalDataSource.getProductsFromDB() } returns listOf()
        coEvery { productRemoteDataSource.getProducts() } returns Response.success(
            ProductList(
                listOf())
        )
        coEvery { productLocalDataSource.saveProductsToDB(listOf()) } just runs

        repository.getProducts()

        coVerify { productLocalDataSource.getProductsFromDB() }
        coVerify { productRemoteDataSource.getProducts() }
        coVerify { productLocalDataSource.saveProductsToDB(listOf()) }
    }

    @Test
    fun getProductsFromDBWhenDBHasValues() = runTest {
        val mockProduct = Product("1234",
            "test_url",
            10.23,
            11.50,
            "test_title",
            11,
            0)

        coEvery { productLocalDataSource.getProductsFromDB() } returns listOf(mockProduct)

        repository.getProducts()

        assertThat(repository.getProducts()).isEqualTo(listOf(mockProduct))
    }
}