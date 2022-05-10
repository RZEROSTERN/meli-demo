package com.rzerocorp.melidemo.data.models

import com.squareup.moshi.Json

data class Paging(
    @Json(name="total")
    val total: Int,
    @Json(name="offset")
    val offset: Int,
    @Json(name="limit")
    val limit: Int
)
