package com.example.post.model

data class ProductRepository(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)