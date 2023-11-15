package ru.viklover.cards.api.v1.controller

import jakarta.annotation.Nullable
import kotlinx.coroutines.flow.Flow

import org.springframework.data.repository.query.Param

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import ru.viklover.cards.api.v1.service.CardService
import ru.viklover.cards.repository.Card

@RestController()
@RequestMapping("/api/v1/cards")
class CardController(
    private val cardService: CardService
) {

    @GetMapping()
    suspend fun findAll(
        @Param("limit") @Nullable limit: Int?, @Param("offset") @Nullable offset: Int?
    ): Flow<Card> = cardService.findAll(limit, offset)

    @GetMapping("/free")
    suspend fun findFreeCards(
        @Param("limit") @Nullable limit: Int?, @Param("offset") @Nullable offset: Int?
    ): Flow<Card> = cardService.findFreeCards(limit, offset)

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable("id") id: Long): Card = cardService.findById(id)
}
