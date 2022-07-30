package com.polyakov.currencieskotlin.service

interface GiphyService {
    fun getGif(tag: String): ByteArray
}