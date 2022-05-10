package com.rzerocorp.melidemo.data.repositories.products

import com.rzerocorp.melidemo.data.models.Product
import com.rzerocorp.melidemo.data.models.responses.SearchResponse
import com.rzerocorp.melidemo.data.network.RestApi
import javax.inject.Inject

class ProductsRepositoryImp @Inject constructor(private val restApi: RestApi): ProductsRepository {
    override suspend fun searchByQuery(query: String, offset: Int): SearchResponse = restApi.searchByQuery(query, offset)
    override suspend fun getProductById(id: String): Product = restApi.getProductByID(id)
}