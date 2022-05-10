package com.rzerocorp.melidemo.data.models

import com.squareup.moshi.Json

data class Product (
    @Json(name="id")
    val id: String,
    @Json(name="title")
    val title: String,
    @Json(name="thumbnail")
    val thumbnail: String,
    @Json(name = "price")
    val price: Double,
    @Json(name="installments")
    val installments: Installment?,
    @Json(name="shipping")
    val shipping: Shipping
)