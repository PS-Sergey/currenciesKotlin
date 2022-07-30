package com.polyakov.currencieskotlin.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "giphyFeign", url = "\${giphy.url}")
interface GiphyFeign {
    @GetMapping("/random")
    fun getGif(@RequestParam("api_key") apiKey: String,
               @RequestParam("tag") tag: String): String

}