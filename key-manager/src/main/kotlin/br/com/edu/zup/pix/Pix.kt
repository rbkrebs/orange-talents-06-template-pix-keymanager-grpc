package br.com.edu.zup.pix

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Pix (val idCliente : String,
           val tipoChave : String,
           val chave : String,
           val tipoConta: String
){

    @Id
    @GeneratedValue
    var id : Long? = null

    val criadoEm: LocalDateTime = LocalDateTime.now()
}