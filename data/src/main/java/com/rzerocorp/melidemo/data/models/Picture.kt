package com.rzerocorp.melidemo.data.models

import com.squareup.moshi.Json

data class Picture(
    @Json(name="id")
    val id: String,
    @Json(name="url")
    val url: String,
    @Json(name="secure_url")
    val secure_url: String,
    @Json(name="size")
    val size: String,
    @Json(name="max_size")
    val max_size: String
)
