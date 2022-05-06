package com.rzerocorp.melidemo.data.models.responses

import com.rzerocorp.melidemo.data.models.Product
import com.squareup.moshi.Json

data class SearchResponse(
    @Json(name = "site_id")
    val site_id: String,
    @Json(name="results")
    val results: List<Product>
)
