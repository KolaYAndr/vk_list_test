package com.example.vk_list_test.presentation.fragments.product_list.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.vk_list_test.domain.product.model.Product

val productCallback = object : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem == newItem
}
