package ru.viklover.cards.api.v1.service

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

import org.springframework.stereotype.Service

import ru.viklover.cards.contracts.v1.models.CardDto
import ru.viklover.cards.contracts.v1.models.FreeCardDto

import ru.viklover.cards.repository.Card
import ru.viklover.cards.repository.CardRepository

import ru.viklover.cards.exception.ItemNotFoundException
import ru.viklover.cards.api.v1.exception.CardHasOwnerException
import ru.viklover.cards.api.v1.exception.CardIsBlockedException
import ru.viklover.cards.api.v1.exception.CardNotIssuedException

@Service
class CardsServiceImpl(
    private val cardRepository: CardRepository
) : CardsService {

    private suspend fun findById(id: Long) =
        cardRepository.findById(id) ?: throw ItemNotFoundException.of(Card::class, id)

    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun generateCardsAsync(range: Int) {
        GlobalScope.launch {
            (1..range).forEach { _ ->
                cardRepository.createCard()
            }
        }
    }

    override suspend fun releaseCard(cardId: Long, customerId: Long) {

        val card = findById(cardId)

        if (card.isOwned) {
            throw CardHasOwnerException(cardId)
        }

        if (card.isBlocked) {
            throw CardIsBlockedException(cardId)
        }

        cardRepository.releaseCard(cardId, customerId)
    }

    override suspend fun blockCard(cardId: Long) {

        val card = findById(cardId)

        if (!card.isOwned) {
            throw CardNotIssuedException(cardId)
        }

        if (card.isBlocked) {
            throw CardIsBlockedException(cardId)
        }

        cardRepository.blockCard(cardId)
    }

    override fun findFreeCards(limit: Int?, offset: Int?): Flow<FreeCardDto> {
        return cardRepository.findByOwned(isOwned = false, limit, offset).map {
            FreeCardDto(id = it.id, number = it.number, createdAt = it.createdAt.toString())
        }
    }

    override fun findAll(limit: Int?, offset: Int?): Flow<CardDto> {
        return cardRepository.findAllBy(limit, offset).map {
            CardDto(
                id = it.id,
                number = it.number,
                createdAt = it.createdAt.toString(),
                releasedAt = it.releasedAt?.toString(),
                blockedAt = it.blockedAt?.toString(),
                customerId = it.customerId,
                isOwned = it.isOwned,
                isBlocked = it.isBlocked
            )
        }
    }
}
