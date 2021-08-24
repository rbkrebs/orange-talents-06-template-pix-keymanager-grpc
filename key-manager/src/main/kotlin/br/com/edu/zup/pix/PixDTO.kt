package br.com.edu.zup.pix


import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull


@Introspected
data class PixDTO( @field:NonNull val idCliente : String,
                    @field:NonNull val tipoChave : String,
                    @field:NonNull val chave : String,
                    @field:NonNull val tipoConta: String) {


}