package ru.viklover.cards.api.v1.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import org.springframework.stereotype.Service

import ru.viklover.cards.repository.CardRepository
import ru.viklover.cards.exception.ItemNotFoundException

import ru.viklover.cards.contracts.v1.models.Card;
import ru.viklover.cards.contracts.v1.models.FreeCard;

@Service
class CardServiceImpl(
    private val cardRepository: CardRepository
) : CardService {

    suspend fun findById(id: Long) =
        cardRepository.findById(id) ?: throw ItemNotFoundException.of(Card::class, id)

    override fun findFreeCards(limit: Int?, offset: Int?): Flow<FreeCard> {
        return cardRepository.findByOwned(isOwned = false, limit, offset).map {
            FreeCard(id = it.id, number = it.number)
        }
    }

    override fun findAll(limit: Int?, offset: Int?): Flow<Card> {
        return cardRepository.findAllBy(limit, offset).map {
            Card(id = it.id, number = it.number, isOwned = it.isOwned)
        }
    }
}
