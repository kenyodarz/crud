package com.bykenyodarz.crud.services

import com.bykenyodarz.crud.models.Persona
import com.bykenyodarz.crud.repositories.PersonaRepository
import com.bykenyodarz.crud.services.apis.PersonaServiceAPI
import com.bykenyodarz.crud.shared.GenericServiceImpl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class PersonaServiceImpl(repository: PersonaRepository) : GenericServiceImpl<Persona, Long>(), PersonaServiceAPI {

    final var repository: PersonaRepository? = null

    init {
        repository.also { this.repository = it }
    }

    override fun getRepository(): JpaRepository<Persona, Long> {
        return this.repository!!
    }
}