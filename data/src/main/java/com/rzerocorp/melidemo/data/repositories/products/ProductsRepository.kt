package com.rzerocorp.melidemo.data.repositories.products

import com.rzerocorp.melidemo.data.models.Product
import com.rzerocorp.melidemo.data.models.responses.SearchResponse

interface ProductsRepository {
    suspend fun searchByQuery(query: String, offset: Int): SearchResponse

    suspend fun getProductById(id: String): Product
}