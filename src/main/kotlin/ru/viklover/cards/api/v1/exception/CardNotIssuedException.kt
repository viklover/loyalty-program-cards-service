package ru.viklover.cards.api.v1.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.TOO_EARLY)
class CardNotIssuedException(id: Long) : RuntimeException("It's impossible to block an unissued card (id: $id)")
