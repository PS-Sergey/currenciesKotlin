package com.polyakov.currencieskotlin.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class CurrencyRateNotFoundException(message: String): Exception(message) {
}