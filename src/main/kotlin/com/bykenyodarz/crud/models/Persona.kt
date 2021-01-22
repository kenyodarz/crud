package com.bykenyodarz.crud.models

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "personas")
class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column
    @NotBlank
    var nombre: String?=null

    @Column
    @NotBlank
    var apellido: String?=null

    @Column
    var direccion: String?=null

    @Column
    var telefono: String?=null

}
