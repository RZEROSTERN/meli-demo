package com.rzerocorp.melidemo.data.models

import com.squareup.moshi.Json

data class ProductDescription(
    @Json(name="text")
    val text: String,
    @Json(name = "plain_text")
    val plain_text: String
)
