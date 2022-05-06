package com.rzerocorp.melidemo.data.network

import com.rzerocorp.melidemo.data.models.responses.SearchResponse
import retrofit2.http.GET

interface RestApi {
    @GET("/sites/MLA/search?q=Motorola")
    suspend fun searchByQuery(): SearchResponse
}