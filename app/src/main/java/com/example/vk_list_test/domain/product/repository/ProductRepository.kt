package com.example.vk_list_test.domain.product.repository

import androidx.paging.PagingData
import com.example.vk_list_test.domain.product.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<PagingData<Product>>

    suspend fun getAllProducts(): List<Product>

    suspend fun getProductById(id: Int): Product
}