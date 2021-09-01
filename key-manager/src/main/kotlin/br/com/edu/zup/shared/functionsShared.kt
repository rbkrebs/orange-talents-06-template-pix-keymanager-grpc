package br.com.edu.zup.shared

import br.com.edu.zup.SolicitacaoRequest
import br.com.edu.zup.novaChave.NovaChaveDTO
import br.com.edu.zup.novaChave.TipoChave
import br.com.edu.zup.novaChave.TipoConta

import br.com.edu.zup.TipoChave.*
import br.com.edu.zup.TipoConta.*

fun SolicitacaoRequest.toModel() : NovaChaveDTO {

    return NovaChaveDTO(
        clienteId = codigoInterno,
        tipoChave = when (tipoChave) {
            DESCONHECIDO_TIPOCHAVE -> null
            else -> TipoChave.valueOf(tipoChave.name)
        },
        valorChave = valorChave,
        tipoConta = when (tipoConta) {
            DESCONHECIDO_TIPOCONTA -> null
            else -> TipoConta.valueOf(tipoConta.name)
        })

}