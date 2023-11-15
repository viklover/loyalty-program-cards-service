package ru.viklover.cards.api.v1.service

import kotlinx.coroutines.flow.Flow

import org.springframework.stereotype.Service

import ru.viklover.cards.repository.Card
import ru.viklover.cards.repository.CardRepository

import ru.viklover.cards.exception.ItemNotFoundException

@Service
class CardService(
    private val cardRepository: CardRepository
) {

    suspend fun findById(id: Long) =
        cardRepository.findById(id) ?: throw ItemNotFoundException.of(Card::class, id)

    suspend fun findFreeCards(limit: Int?, offset: Int?): Flow<Card> =
        cardRepository.findByOwned(isOwned = false, limit, offset);

    suspend fun findAll(limit: Int?, offset: Int?): Flow<Card> =
        cardRepository.findAllBy(limit, offset)
}
