package com.bykenyodarz.crud.shared

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import java.io.Serializable
import java.util.function.Consumer

@Service
abstract class GenericServiceImpl<T, ID : Serializable> : GenericServiceAPI<T, ID> {

    override fun getAll(): List<T> {
        val returnList: MutableList<T> = ArrayList()
        getRepository().findAll().forEach(Consumer(returnList::add))
        return returnList
    }

    override fun getOne(id: ID): T? {
        // Usamos un Optional implementado en Java8
        val optional = getRepository().findById(id)
        //Retornamos nulo en caso de no encontrar el objeto
        return optional.orElse(null)
    }

    override fun save(entity: T): T {
        return getRepository().save(entity!!)
    }

    override fun delete(id: ID) {
        getRepository().deleteById(id)
    }

    abstract override fun getRepository(): JpaRepository<T, ID>


}