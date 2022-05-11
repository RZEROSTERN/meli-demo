package com.rzerocorp.melidemo.data.models

import com.squareup.moshi.Json

data class City(
    @Json(name="id")
    val id: String,
    @Json(name="name")
    val name: String
)
