package com.polyakov.currencieskotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class CurrenciesKotlinApplication

fun main(args: Array<String>) {
    runApplication<CurrenciesKotlinApplication>(*args)
}
