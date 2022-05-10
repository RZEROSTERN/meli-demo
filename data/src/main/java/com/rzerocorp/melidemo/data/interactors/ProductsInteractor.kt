package com.rzerocorp.melidemo.data.interactors

import com.rzerocorp.melidemo.data.models.Product
import com.rzerocorp.melidemo.data.models.responses.SearchResponse

interface ProductsInteractor {
    suspend fun searchByQuery(query: String, offset: Int): SearchResponse

    suspend fun getProductByID(id: String): Product
}