package com.rzerocorp.melidemo.data.models

import com.squareup.moshi.Json

data class Product (
    @Json(name="id")
    val id: String,
    @Json(name="title")
    val title: String,
    @Json(name="seller_id")
    val seller_id: Int,
    @Json(name="thumbnail")
    val thumbnail: String,
    @Json(name = "price")
    val price: Double,
    @Json(name="installments")
    val installments: Installment?,
    @Json(name="shipping")
    val shipping: Shipping,
    @Json(name="pictures")
    val pictures: List<Picture>?,
    @Json(name="sold_quantity")
    val sold_quantity: Int,
    @Json(name="available_quantity")
    val available_quantity: Int,
    @Json(name="seller_address")
    val seller_address: SellerAddress,
    var description: ProductDescription?,
    var seller: Seller?
)