package ru.viklover.cards.repository

import org.springframework.stereotype.Repository

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

import kotlinx.coroutines.flow.Flow

@Repository
interface CardRepository : CoroutineCrudRepository<Card, Long> {

    @Query("select * from cards.card limit :limit offset :offset")
    suspend fun findAllBy(limit: Int?, offset: Int?): Flow<Card>;

    @Query("select * from cards.card where is_owned = :isOwned limit :limit offset :offset")
    suspend fun findByOwned(isOwned: Boolean, limit: Int?, offset: Int?): Flow<Card>;
}
