package ru.viklover.cards.repository

import java.time.LocalDateTime

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
class Card(
    @Id
    val id: Long,
    val number: String,
    var status: CardStatus,
    var customerId: Long? = null,
    val createdAt: LocalDateTime,
    val releasedAt: LocalDateTime? = null,
    val blockedAt: LocalDateTime? = null
)
