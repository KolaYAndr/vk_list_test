package com.example.vk_list_test.presentation.fragments.product_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_list_test.domain.product.model.Product
import com.example.vk_list_test.domain.product.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val repository: ProductRepository): ViewModel() {
    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> get() = _selectedProduct

    fun getProduct(productId: Int) {
        viewModelScope.launch {
            _selectedProduct.value = repository.getProductById(productId)
        }
    }
}