package com.bykenyodarz.crud.shared

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import java.io.Serializable
import java.util.*
import javax.validation.Valid


@RestController
abstract class GenericRestController<T, ID : Serializable>(val serviceAPI: GenericServiceAPI<T, ID>) {

    protected fun validar(result: BindingResult): ResponseEntity<*>? {
        val errores: MutableMap<String, Any> = HashMap()
        result.fieldErrors.forEach { err: FieldError ->
            with(errores) { put(err.field, "El campo ${err.field}  ${err.defaultMessage}") }
        }
        return ResponseEntity.badRequest().body(errores)
    }

    @GetMapping("/all")
    fun getAll(): ResponseEntity<List<T>> {
        return ResponseEntity.ok(serviceAPI.getAll())
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: ID): ResponseEntity<Any> {
        val entity: T = serviceAPI.getOne(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(entity!!)
    }

    @PostMapping("/save")
    fun save(@RequestBody @Valid entity: T, result: BindingResult): ResponseEntity<*>? {
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