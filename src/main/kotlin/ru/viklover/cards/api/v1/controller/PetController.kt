package ru.viklover.cards.api.v1.controller

import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import ru.viklover.cards.contracts.controller.PetApiController
import ru.viklover.cards.contracts.models.Pet

class PetController : PetApiController() {

    override suspend fun addPet(pet: Pet?): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getPets(): ResponseEntity<Flow<Pet>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
