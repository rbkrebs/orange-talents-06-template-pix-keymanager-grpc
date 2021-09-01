package br.com.edu.zup.novaChave

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.Optional

@Repository
interface NovaChaveRepository : JpaRepository<NovaChave, Long> {

    fun existsByValorChave(valorChave: String?): Boolean


}