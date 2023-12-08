package ru.viklover.cards.api.v1.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
class CardIsBlockedException(id: Long) : RuntimeException("Card with id '$id' is blocked now")
