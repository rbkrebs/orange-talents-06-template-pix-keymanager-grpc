package br.com.edu.zup.shared


import br.com.edu.zup.grpc.SolicitacaoRequest
import br.com.edu.zup.grpc.SolicitacaoResponse
import br.com.edu.zup.novaChave.NovaChaveDTO

import br.com.edu.zup.grpc.TipoDeChave.*
import br.com.edu.zup.grpc.TipoDeConta.*
import br.com.edu.zup.novaChave.TipoChave
import br.com.edu.zup.novaChave.TipoConta
import com.google.protobuf.Any
import com.google.rpc.BadRequest
import com.google.rpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.protobuf.StatusProto
import io.grpc.stub.StreamObserver
import javax.validation.ConstraintViolationException



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

fun handleConstraintViolationException(e: ConstraintViolationException): StatusRuntimeException{

    e.printStackTrace()

    val violations = e.constraintViolations.map { it ->
        BadRequest.FieldViolation.newBuilder()
            .setField(it.propertyPath.last().name)
            .setDescription(it.message)
            .build()
    }

    val details = BadRequest.newBuilder()
        .addAllFieldViolations(violations)
        .build()

    val statusProto = Status.newBuilder()
        .setCode(io.grpc.Status.INVALID_ARGUMENT.code.value())
        .setMessage("Parâmetros de entrada inválidos")
        .addDetails(Any.pack(details))
        .build()

    val exception = StatusProto.toStatusRuntimeException(statusProto);

    return exception

}