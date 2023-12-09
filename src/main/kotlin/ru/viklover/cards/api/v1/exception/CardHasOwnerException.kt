package ru.viklover.cards.api.v1.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
class CardHasOwnerException(id: Long) : RuntimeException("Card with id '$id' is already owned by another customer")
