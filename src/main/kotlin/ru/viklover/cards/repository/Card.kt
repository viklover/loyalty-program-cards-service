package ru.viklover.cards.repository

import java.util.UUID
import java.time.LocalDateTime

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table
class Card(
    var number: String,
    var isOwned: Boolean,
    var isBlocked: Boolean,
    var customerId: Long? = null,
    val releasedAt: LocalDateTime? = null,
    val blockedAt: LocalDateTime? = null
) {

    @Id @Column("id")
    private var _id: Long? = null

    @Column("created_at")
    private var _createdAt: LocalDateTime? = null;

    val id
        get() = checkNotNull(_id)

    val createdAt
        get() = checkNotNull(_createdAt)

    companion object {
        fun generate(): Card {
            return Card(number = UUID.randomUUID().toString(), isBlocked = false, isOwned = false)
        }
    }
}
