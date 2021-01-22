package com.bykenyodarz.crud.repositories

import com.bykenyodarz.crud.models.Persona
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonaRepository: JpaRepository<Persona, Long> {
}