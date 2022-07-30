package com.polyakov.currencieskotlin.feign

import com.polyakov.currencieskotlin.vo.OpenExchangeRatesVo
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "openExchangeRatesFeign", url = "\${openexchangerates.url}")
interface OpenExchangeRatesFeign {
    @GetMapping("/currencies.json")
    fun getCurrencies(): Map<String, String>

    @GetMapping("/historical/{date}.json")
    fun geHistorical(
        @PathVariable("date") date: String,
        @RequestParam("app_id") appId: String,
        @RequestParam("base") base: String,
        @RequestParam("symbols") symbols: String): OpenExchangeRatesVo
}