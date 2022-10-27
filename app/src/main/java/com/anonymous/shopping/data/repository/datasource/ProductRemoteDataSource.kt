package com.anonymous.shopping.data.repository.datasource

import com.anonymous.shopping.data.api.ProductService
import com.anonymous.shopping.data.model.ProductList
import retrofit2.Response
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(
    private val productService: ProductService
) {
suspend fun getProducts(): Response<ProductList> = productService.getProducts()
}