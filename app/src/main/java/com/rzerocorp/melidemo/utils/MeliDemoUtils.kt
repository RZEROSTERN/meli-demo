package com.rzerocorp.melidemo.utils

import java.text.NumberFormat

fun Double.formatAsCurrency(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}

fun String.convertToHttps(): String {
    return this.replace("http://", "https://")
}