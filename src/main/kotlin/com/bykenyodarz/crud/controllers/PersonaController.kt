package com.bykenyodarz.crud.controllers

import com.bykenyodarz.crud.models.Persona
import com.bykenyodarz.crud.services.apis.PersonaServiceAPI
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class PersonaController(serviceAPI: PersonaServiceAPI) {

    private final val serviceAPI: PersonaServiceAPI

    init {
        serviceAPI.also { this.serviceAPI = it }
    }

    @RequestMapping("/")
    fun index(model: Model): String {
        model.addAttribute("list", serviceAPI.getAll())
        return "index"
    }

    @GetMapping("/save/{id}")
    fun showSave(@PathVariable("id") id: Long, model: Model): String {

        val persona: Persona? = serviceAPI.getOne(id)

        if (persona != null) {
            model.addAttribute("persona", persona)
        } else {
            model.addAttribute("persona", Persona())
        }
        return "save"
    }

    @PostMapping("/save")
    fun save(persona: Persona, model: Model): String {
        serviceAPI.save(persona)
        return "redirect:/"
    }

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): String {
        serviceAPI.delete(id)
        return "redirect:/"
    }


}