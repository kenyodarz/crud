package com.bykenyodarz.crud.controllers

import com.bykenyodarz.crud.models.Persona
import com.bykenyodarz.crud.services.apis.PersonaServiceAPI
import com.bykenyodarz.crud.shared.GenericRestController
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/personas")
class PersonaRestController(serviceAPI: PersonaServiceAPI) : GenericRestController<Persona, Long>(serviceAPI)