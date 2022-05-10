package com.rzerocorp.melidemo.data.models

import com.squareup.moshi.Json

data class Installment(
    @Json(name="quantity")
    val quantity: Int,
    @Json(name="amount")
    val amount: Double
)
