package com.polyakov.currencieskotlin.vo

data class OpenExchangeRatesVo(
    val disclaimer: String,
    val license: String,
    val timestamp: Int,
    val base: String,
    val rates: Map<String, Double>
)