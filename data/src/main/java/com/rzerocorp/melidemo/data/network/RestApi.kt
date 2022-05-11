package com.rzerocorp.melidemo.data.network

import com.rzerocorp.melidemo.data.models.Product
import com.rzerocorp.melidemo.data.models.ProductDescription
import com.rzerocorp.melidemo.data.models.Seller
import com.rzerocorp.melidemo.data.models.SellerAddress
import com.rzerocorp.melidemo.data.models.responses.SearchResponse
import com.rzerocorp.melidemo.data.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {
    @GET("/sites/" + Constants.STORE_ID + "/search")
    suspend fun searchByQuery(
        @Query("q") query: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = Constants.PAGE_LIMIT
    ): SearchResponse

    @GET("items/{id}")
    suspend fun getProductByID(@Path("id") id: String): Product

    @GET("items/{id}/description")
    suspend fun getProductDescription(@Path("id") id: String): ProductDescription

    @GET("users/{id}")
    suspend fun getSellerInfo(@Path("id") id: String): Seller
}