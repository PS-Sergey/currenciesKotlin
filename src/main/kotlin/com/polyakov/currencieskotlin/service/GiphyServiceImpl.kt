package com.polyakov.currencieskotlin.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.polyakov.currencieskotlin.exception.GifNotFoundException
import com.polyakov.currencieskotlin.feign.GiphyFeign
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class GiphyServiceImpl @Autowired constructor(
    val giphyFeign: GiphyFeign,
    val restTemplate: RestTemplate,
    val objectMapper: ObjectMapper
    ): GiphyService {

    @Value("\${giphy.api.key}")
    lateinit var apiKey: String

    override fun getGif(tag: String): ByteArray {
        val json = giphyFeign.getGif(apiKey, tag)
        val gifUrl = getGifUrl(json)
        val requestEntity = RequestEntity
            .get(gifUrl)
            .build()
        val responseEntity = restTemplate.exchange(requestEntity, ByteArray::class.java)
        return responseEntity.body ?: throw GifNotFoundException("Gif not found")
    }

    fun getGifUrl(json: String): String {
        return objectMapper.readTree(json).get("data").get("images").get("original").get("url").asText()
    }
}