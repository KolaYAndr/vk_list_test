package com.example.vk_list_test.presentation.fragments.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.vk_list_test.domain.product.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(repository: ProductRepository) :
    ViewModel() {
    private val _products = repository.getProducts().cachedIn(viewModelScope)
    val products get() = _products
}