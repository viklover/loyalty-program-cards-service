package ru.viklover.cards.api.v1.controller

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

import ru.viklover.cards.contracts.v1.models.CardDto
import ru.viklover.cards.contracts.v1.models.FreeCardDto
import ru.viklover.cards.contracts.v1.controller.CardsControllerV1

import ru.viklover.cards.api.v1.service.CardService

@RestController
class CardController(
    private val cardService: CardService
) : CardsControllerV1 {

    override suspend fun blockCard(cardId: Long): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun generateCards(range: Int): ResponseEntity<Unit> {
        GlobalScope.launch { cardService.generateCards(range) }
        return ResponseEntity(HttpStatus.OK)
    }

    override fun getCards(limit: Int?, offset: Int?): ResponseEntity<Flow<CardDto>> {
        return ResponseEntity.ok(cardService.findAll(limit, offset));
    }

    override fun getFreeCards(limit: Int?, offset: Int?): ResponseEntity<Flow<FreeCardDto>> {
        return ResponseEntity.ok(cardService.findFreeCards(limit, offset))
    }

    override suspend fun releaseCard(customerId: Long, cardId: Long): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.OK)
    }
}