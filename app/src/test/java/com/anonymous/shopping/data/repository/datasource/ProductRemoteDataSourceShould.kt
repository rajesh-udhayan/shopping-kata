package com.anonymous.shopping.data.repository.datasource

import com.anonymous.shopping.data.api.ProductService
import com.anonymous.shopping.utils.BaseUnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ProductRemoteDataSourceShould: BaseUnitTest() {

    @Test
    fun getProductsFromAPIService() = runTest {
        val productService = mockk<ProductService>()
        val productRemoteDataSource = ProductRemoteDataSource(productService)

        coEvery { productService.getProducts() } returns mockk()
        productRemoteDataSource.getProducts()

        coVerify { productService.getProducts() }
    }
}