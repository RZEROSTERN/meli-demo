package com.rzerocorp.melidemo.data.interactors

import com.rzerocorp.melidemo.data.models.responses.SearchResponse

interface ProductsInteractor {
    suspend fun searchByQuery(query: String): SearchResponse
}