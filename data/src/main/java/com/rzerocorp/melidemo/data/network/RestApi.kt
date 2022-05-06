package com.rzerocorp.melidemo.data.network

import com.rzerocorp.melidemo.data.models.responses.SearchResponse
import com.rzerocorp.melidemo.data.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {
    @GET("/sites/" + Constants.STORE_ID + "/search")
    suspend fun searchByQuery(@Query("q") query: String): SearchResponse
}