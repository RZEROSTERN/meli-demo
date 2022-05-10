package com.rzerocorp.melidemo.presentation.products

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rzerocorp.melidemo.data.interactors.ProductsInteractorImp
import com.rzerocorp.melidemo.data.models.Product

class ProductsDataSource (
    private val productsInteractorImp: ProductsInteractorImp,
    private val query: String): PagingSource<Int, Product>() {

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val nextPage: Int = params.key ?: 1
            val response = productsInteractorImp.searchByQuery(query, nextPage)

            LoadResult.Page(data = response.results, null, nextPage + 1)
        } catch(e: Exception) {
            LoadResult.Error(e)
        }
    }
}