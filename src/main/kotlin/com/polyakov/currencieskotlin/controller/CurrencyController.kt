package com.polyakov.currencieskotlin.controller

import com.polyakov.currencieskotlin.service.GiphyService
import com.polyakov.currencieskotlin.service.OpenExchangeRatesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class CurrencyController @Autowired constructor(
    val openExchangeRatesService: OpenExchangeRatesService,
    val giphyService: GiphyService
) {
    @Value("\${giphy.rich}")
    lateinit var rich: String

    @Value("\${giphy.broke}")
    lateinit var broke: String

    @Value("\${giphy.strong}")
    lateinit var strong: String

    @GetMapping("/currencies")
    fun getCurrencies(): Map<String, String>  {
        return openExchangeRatesService.getCurrencies();
    }

    @GetMapping(value = ["/rate/{code}"], produces = [MediaType.IMAGE_GIF_VALUE])
    fun getRate(@PathVariable code: String ): ResponseEntity<ByteArray> {
        var tag = ""
        val counting = openExchangeRatesService.compareRate(code)
        when (counting) {
            1 -> tag = rich
            -1 -> tag = broke
            0 -> tag = strong
        }
        val gif = giphyService.getGif(tag)
        return ResponseEntity
            .ok()
            .contentType(MediaType.IMAGE_GIF)
            .body(gif);
    }
}