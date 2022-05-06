package com.rzerocorp.melidemo.data.models.responses

import com.squareup.moshi.Json

data class SearchResponse(
    @Json(name = "site_id")
    val site_id: String
)
