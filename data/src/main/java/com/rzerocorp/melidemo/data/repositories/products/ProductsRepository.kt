package com.rzerocorp.melidemo.data.repositories.products

import com.rzerocorp.melidemo.data.models.responses.SearchResponse

interface ProductsRepository {
    suspend fun searchByQuery(): SearchResponse
}