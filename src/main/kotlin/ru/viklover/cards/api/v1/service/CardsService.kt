package ru.viklover.cards.api.v1.service

import kotlinx.coroutines.flow.Flow

import ru.viklover.cards.contracts.v1.models.CardDto
import ru.viklover.cards.contracts.v1.models.FreeCardDto

interface CardsService {
    suspend fun generateCardsAsync(range: Int)
    suspend fun releaseCard(cardId: Long, customerId: Long)
    suspend fun blockCard(cardId: Long)
    fun findAll(limit: Int? = 50, offset: Int? = 0): Flow<CardDto>
    fun findFreeCards(limit: Int? = 50, offset: Int? = 0): Flow<FreeCardDto>
}
