package com.example.vk_list_test.data.api

import com.example.vk_list_test.data.product.response.ProductResponse
import com.example.vk_list_test.domain.product.model.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DummyApi {
    companion object {
        const val BASE_ENDPOINT = "https://dummyjson.com"
    }

    @GET("/products")
    suspend fun getProducts(@Query("limit") limit: Int, @Query("skip") skip: Int): ProductResponse

    @GET("/products/{id}")
    suspend fun getProductById(@Path("id") id: Int) : Product
}