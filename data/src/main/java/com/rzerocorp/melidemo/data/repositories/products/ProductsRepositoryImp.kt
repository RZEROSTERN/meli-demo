package com.rzerocorp.melidemo.data.repositories.products

import com.rzerocorp.melidemo.data.models.responses.SearchResponse
import com.rzerocorp.melidemo.data.network.RestApi
import javax.inject.Inject

class ProductsRepositoryImp @Inject constructor(private val restApi: RestApi): ProductsRepository {
    override suspend fun searchByQuery(): SearchResponse = restApi.searchByQuery()
}