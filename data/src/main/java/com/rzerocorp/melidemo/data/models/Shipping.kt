package com.rzerocorp.melidemo.data.models

import com.squareup.moshi.Json

data class Shipping(
    @Json(name="free_shipping")
    val free_shipping: Boolean
)
