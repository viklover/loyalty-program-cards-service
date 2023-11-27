package ru.viklover.cards.api.v1.service

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import org.springframework.stereotype.Service

import ru.viklover.cards.contracts.v1.models.CardDto
import ru.viklover.cards.contracts.v1.models.FreeCardDto

import ru.viklover.cards.repository.Card
import ru.viklover.cards.repository.CardRepository
import ru.viklover.cards.exception.ItemNotFoundException

@Service
class CardServiceImpl(
    private val cardRepository: CardRepository
) : CardService {

    suspend fun findById(id: Long) =
        cardRepository.findById(id) ?: throw ItemNotFoundException.of(CardDto::class, id)

    override suspend fun generateCards(range: Int) = coroutineScope {
        (0..range).forEach { _ ->
            cardRepository.save(Card.generate())
        }
    }

    override fun findFreeCards(limit: Int?, offset: Int?): Flow<FreeCardDto> {
        return cardRepository.findByOwned(isOwned = false, limit, offset).map {
            FreeCardDto(id = it.id(), number = it.number)
        }
    }

    override fun findAll(limit: Int?, offset: Int?): Flow<CardDto> {
        return cardRepository.findAllBy(limit, offset).map {
            CardDto(id = it.id(), number = it.number, isOwned = it.isOwned)
        }
    }
}
