package ru.viklover.cards.api.v1.controller

import kotlinx.coroutines.flow.Flow

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

import ru.viklover.cards.contracts.v1.models.Card
import ru.viklover.cards.contracts.v1.models.FreeCard
import ru.viklover.cards.contracts.v1.controller.CardsControllerV1

import ru.viklover.cards.api.v1.service.CardService

@RestController
class CardController(
    private val cardService: CardService
) : CardsControllerV1 {

    override suspend fun blockCard(cardId: Long): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override suspend fun generateCards(range: Long): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getCards(limit: Int?, offset: Int?): ResponseEntity<Flow<Card>> {
        return ResponseEntity.ok(cardService.findAll(limit, offset));
    }

    override fun getFreeCards(limit: Int?, offset: Int?): ResponseEntity<Flow<FreeCard>> {
        return ResponseEntity.ok(cardService.findFreeCards(limit, offset))
    }

    override suspend fun releaseCard(customerId: Long, cardId: Long): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.OK)
    }
}