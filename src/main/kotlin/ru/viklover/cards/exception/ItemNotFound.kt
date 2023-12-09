package ru.viklover.cards.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

import kotlin.reflect.KClass

@ResponseStatus(HttpStatus.NOT_FOUND)
open class ItemNotFoundException(id: Long, className: String) :
    RuntimeException("$className with id: $id does not exists") {

    companion object {
        inline fun <reified T : Any> of(clazz: KClass<T>, id: Long): ItemNotFoundException {
            return ItemNotFoundException(id, clazz.java.name)
        }
    }
}
