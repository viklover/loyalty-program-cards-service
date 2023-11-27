package ru.viklover.cards.api.v1.service

import kotlinx.coroutines.flow.Flow

import ru.viklover.cards.contracts.v1.models.CardDto
import ru.viklover.cards.contracts.v1.models.FreeCardDto

interface CardService {
    suspend fun generateCards(range: Int)
    fun findFreeCards(limit: Int?, offset: Int?): Flow<FreeCardDto>
    fun findAll(limit: Int?, offset: Int?): Flow<CardDto>
}
