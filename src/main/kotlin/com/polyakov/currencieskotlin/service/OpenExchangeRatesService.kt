package com.polyakov.currencieskotlin.service

interface OpenExchangeRatesService {
    fun getCurrencies(): Map<String, String>
    fun compareRate(code: String): Int
}