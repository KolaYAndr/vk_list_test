package com.example.vk_list_test.data.product.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.vk_list_test.data.api.DummyApi
import com.example.vk_list_test.domain.product.model.Product
import retrofit2.HttpException
import java.io.IOException
import kotlin.math.max


class ProductPagingSource(
    private val dummyApi: DummyApi
) : PagingSource<Int, Product>() {
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val closestItem = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(closestItem.id - (state.config.pageSize / 2))
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val position = params.key ?: 0
        val skip = position * params.loadSize

        return try {
            val response =  dummyApi.getProducts(limit = params.loadSize, skip = skip)

            val items = response.products
            LoadResult.Page(
                data = items,
                prevKey = if (position == 0) null else position - 1,
                nextKey = if (items.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    private fun ensureValidKey(key: Int) = max(0, key)
}