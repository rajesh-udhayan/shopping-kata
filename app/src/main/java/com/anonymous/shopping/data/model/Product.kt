package com.anonymous.shopping.data.model

data class Product(
    val id: String,
    val title: String,
    val price: Double,
    val imageUrl: String,
    val isFavorite: Boolean
)
