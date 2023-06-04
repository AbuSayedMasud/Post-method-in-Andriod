package com.example.post.api

import com.example.post.model.Product
import com.example.post.model.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/products/add")
    suspend fun createProduct(@Body product: Product): Response<ProductResponse>
}
