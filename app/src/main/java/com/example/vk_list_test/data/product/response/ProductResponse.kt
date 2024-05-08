package com.example.vk_list_test.data.product.response

import com.example.vk_list_test.domain.product.model.Product

data class ProductResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)