package com.anonymous.shopping.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    val id: String,
    val imageURL: String,
    val price: List<Price>,
    val ratingCount: Double,
    val title: String,
    val totalReviewCount: Int,
    val isFavorite: Boolean
)

data class Price(
    val value: Double
)