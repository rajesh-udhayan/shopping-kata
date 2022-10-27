package com.anonymous.shopping.data.api

import com.anonymous.shopping.data.model.ProductList
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {

    @GET(".")
    suspend fun getProducts(): Response<ProductList>
}