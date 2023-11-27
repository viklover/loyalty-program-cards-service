package ru.viklover.cards.repository

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

import java.util.UUID

@Table
class Card(
    @Id @Column("id")
    var id: Long? = null,
    var number: String,
    var isOwned: Boolean
) {
    fun id() = checkNotNull(this.id)

    companion object {
        fun generate(): Card {
            return Card(id = null, number = UUID.randomUUID().toString(), isOwned = false)
        }
    }
}
