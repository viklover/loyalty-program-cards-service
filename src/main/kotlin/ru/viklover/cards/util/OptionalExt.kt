package ru.viklover.cards.util

import java.util.*

fun <T> Optional<T>.toNullable(): T? {
    return if (isEmpty) null else get()
}

fun <T, R> T?.ifNotNull(block: (value: T) -> R): R? {
    return if (this == null) null else block(this)
}
