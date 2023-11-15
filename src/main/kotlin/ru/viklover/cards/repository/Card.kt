package ru.viklover.cards.repository

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Card(
    @Id val id: Long,
    val number: String,
    val isOwned: Boolean
)
