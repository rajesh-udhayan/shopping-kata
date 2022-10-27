package com.anonymous.shopping.data.model

import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("products")
    val products: List<Product>
)
