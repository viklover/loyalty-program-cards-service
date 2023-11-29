package ru.viklover.cards

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CardsServiceApplication

fun main(args: Array<String>) {
    runApplication<CardsServiceApplication>(*args)
}
