package com.polyakov.currencieskotlin.service

import com.polyakov.currencieskotlin.exception.CurrencyRateNotFoundException
import com.polyakov.currencieskotlin.feign.OpenExchangeRatesFeign
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class OpenExchangeRatesServiceImpl @Autowired constructor(var openExchangeRatesFeign: OpenExchangeRatesFeign): OpenExchangeRatesService {

    @Value("\${openexchangerates.app.id}")
    lateinit var appId: String

    @Value("\${openexchangerates.base}")
    lateinit var base: String

    override fun getCurrencies(): Map<String, String> {
        return openExchangeRatesFeign.getCurrencies()
    }

    override fun compareRate(code: String): Int {
        val today = LocalDate.now();
        val todayRate = getRateByDate(today.toString(), code);
        val yesterdayRate = getRateByDate(today.minusDays(1).toString(), code);
        return todayRate.compareTo(yesterdayRate)
    }

    fun getRateByDate(date: String, code: String): Double {
        val response = openExchangeRatesFeign.geHistorical(date, appId, base, code)
        val rate: Double = response.rates[code]
            ?: throw CurrencyRateNotFoundException(String.format("Currency rate for %s not found", code))
        return 1 / rate
    }
}