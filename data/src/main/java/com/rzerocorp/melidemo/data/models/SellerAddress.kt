package com.rzerocorp.melidemo.data.models

import com.squareup.moshi.Json
import java.math.BigInteger

data class SellerAddress(
    @Json(name="id")
    val id: BigInteger,
    @Json(name="city")
    val city: City,
    @Json(name="state")
    val state: SellerState,
    @Json(name="country")
    val country: Country
)
