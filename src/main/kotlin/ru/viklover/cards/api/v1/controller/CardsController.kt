package ru.viklover.cards.api.v1.controller

import kotlinx.coroutines.flow.Flow

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

import ru.viklover.cards.contracts.v1.models.CardDto
import ru.viklover.cards.contracts.v1.models.FreeCardDto
import ru.viklover.cards.contracts.v1.controller.CardsControllerV1

import ru.viklover.cards.api.v1.service.CardsService

@RestController
class CardsController(
    private val cardsService: CardsService
) : CardsControllerV1 {

    override suspend fun generateCards(range: Int): ResponseEntity<Unit> {
        cardsService.generateCardsAsync(range)
        return ResponseEntity(HttpStatus.OK)
    }

    override suspend fun releaseCard(cardId: Long, customerId: Long): ResponseEntity<Unit> {
        cardsService.releaseCard(cardId, customerId)
        return ResponseEntity(HttpStatus.OK)
    }

    override suspend fun blockCard(cardId: Long): ResponseEntity<Unit> {
        cardsService.blockCard(cardId)
        return ResponseEntity(HttpStatus.OK)
    }

    override fun getCards(limit: Int?, offset: Int?): ResponseEntity<Flow<CardDto>> {
        return ResponseEntity.ok(cardsService.findAll(limit, offset))
    }

    override fun getFreeCards(limit: Int?, offset: Int?): ResponseEntity<Flow<FreeCardDto>> {
        return ResponseEntity.ok(cardsService.findFreeCards(limit, offset))
    }
}