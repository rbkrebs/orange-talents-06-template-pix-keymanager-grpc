package br.com.edu.zup.novaChave

import br.com.edu.zup.clients.ContaCliente
import br.com.edu.zup.validators.ValidChavePix
import br.com.edu.zup.validators.ValidUUID
import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


@ValidChavePix
@Introspected
data class NovaChaveDTO(@field:NotBlank @field:ValidUUID val clienteId: String,
                        @field:NotNull val tipoChave: TipoChave?,
                        @field:Size(max = 77) val valorChave: String,
                        @field: NotNull val tipoConta: TipoConta?) {



    fun toModel(dadosContaCliente: ContaCliente): NovaChave{

        return NovaChave(

            clienteId = UUID.fromString(this.clienteId),
            tipoChave = TipoChave.valueOf(this.tipoChave!!.name),
            valorChave = if (this.tipoChave == TipoChave.CHAVE_ALEATORIA) UUID.randomUUID().toString() else this.valorChave!!,
            tipoConta = TipoConta.valueOf(this.tipoConta!!.name),
            conta = dadosContaCliente


        )
    }

}