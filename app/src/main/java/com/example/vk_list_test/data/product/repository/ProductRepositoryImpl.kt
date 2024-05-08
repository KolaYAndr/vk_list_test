package com.example.vk_list_test.data.product.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.vk_list_test.data.api.DummyApi
import com.example.vk_list_test.data.product.paging.ProductPagingSource
import com.example.vk_list_test.domain.product.model.Product
import com.example.vk_list_test.domain.product.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject


const val NETWORK_PAGE_SIZE = 20

class ProductRepositoryImpl @Inject constructor(private val api: DummyApi) : ProductRepository {
    override fun getProducts(): Flow<PagingData<Product>> =
        Pager(
            config = PagingConfig(NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { ProductPagingSource(api) }
        ).flow
            .flowOn(Dispatchers.IO)

    override suspend fun getAllProducts(): List<Product> =
        api.getProducts(limit = 0, skip = 0).products

    override suspend fun getProductById(id: Int): Product = withContext(Dispatchers.IO) {
        api.getProductById(id)
    }
}
