package ru.viklover.cards.api.v1.service

import kotlinx.coroutines.flow.Flow

import ru.viklover.cards.contracts.v1.models.Card
import ru.viklover.cards.contracts.v1.models.FreeCard

interface CardService {
    fun findFreeCards(limit: Int?, offset: Int?): Flow<FreeCard>
    fun findAll(limit: Int?, offset: Int?): Flow<Card>
}
