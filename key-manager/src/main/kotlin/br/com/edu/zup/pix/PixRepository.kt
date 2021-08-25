package br.com.edu.zup.pix

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.Optional

@Repository
interface PixRepository : JpaRepository<Solicitacao, Long> {


    fun findByCodigo(codigo : String) : Optional<Solicitacao>



}