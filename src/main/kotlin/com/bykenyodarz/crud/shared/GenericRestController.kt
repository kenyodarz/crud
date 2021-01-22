package com.bykenyodarz.crud.shared

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import java.io.Serializable
import java.util.function.Consumer
import javax.validation.Valid
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import java.util.HashMap
import org.springframework.web.bind.annotation.RestController

@RestController
abstract class GenericRestController<T, ID : Serializable>(val serviceAPI: GenericServiceAPI<T, ID>){

    fun validar(result: BindingResult): ResponseEntity<Map<String, Any>> {
        val errores: MutableMap<String, Any> = HashMap()
        result.fieldErrors.forEach(Consumer { err: FieldError ->
            errores[err.field] = " El campo " + err.field + " " + err.defaultMessage
        })
        return ResponseEntity.badRequest().body(errores)
    }

    @GetMapping("/all")
    fun getAll(): ResponseEntity<List<T>>{
        return ResponseEntity.ok(serviceAPI.getAll())
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: ID): ResponseEntity<Any>{
        val entity: T = serviceAPI.getOne(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(entity!!)
    }

    @PostMapping("/save")
    fun save(@RequestBody entity: @Valid T, result: BindingResult): ResponseEntity<*> {
        return if (result.hasErrors()) {
            validar(result)
        } else ResponseEntity.status(HttpStatus.OK).body(serviceAPI.save(entity))
    }

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: ID): ResponseEntity<Any> {
        val entity: T = serviceAPI.getOne(id) ?: return ResponseEntity.notFound().build()
        serviceAPI.delete(id)
        return ResponseEntity.ok(entity!!)
    }





}