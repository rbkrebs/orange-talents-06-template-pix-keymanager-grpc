package br.com.edu.zup.clients

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.validation.constraints.NotBlank


@Embeddable
class ContaCliente(


    @field:NotBlank
    @Column(nullable = false)
    val instituicao: String,

    @field:NotBlank
    @Column(nullable = false)
    val nomeTitular: String,

    @field:NotBlank
    @Column(nullable = false)
    val cpfTitular: String,

    @field:NotBlank
    @Column(nullable = false)
    val agencia: String,

    @field:NotBlank
    @Column(nullable = false)
    val numeroConta: String,

) {

}
