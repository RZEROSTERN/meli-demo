package com.rzerocorp.melidemo.data.models

import com.squareup.moshi.Json

data class Product (
    @Json(name="id")
    val id: String,
    @Json(name="title")
    val title: String,
    @Json(name="thumbnail")
    val thumbnail: String
)