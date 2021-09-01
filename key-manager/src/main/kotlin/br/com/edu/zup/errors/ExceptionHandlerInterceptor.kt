package br.com.edu.zup.errors

import com.google.protobuf.Any
import com.google.rpc.BadRequest
import com.google.rpc.Status
import io.grpc.protobuf.StatusProto
import javax.inject.Singleton
import javax.validation.ConstraintViolationException


@Singleton
class ExceptionHandlerInterceptor(val errors: ConstraintViolationException) {

    val violations = errors.constraintViolations.map { it ->
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




    /* when(statusCode){

                return responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("formato de cpf inválido")
                    .augmentDescription("formato esperado é 12345678901")
                    .asRuntimeException())

                return responseObserver.onError(
                    Status.INVALID_ARGUMENT
                        .withDescription("formato de celular errado")
                        .augmentDescription("formato esperado é +5585988714077")
                        .asRuntimeException()
                )


                return responseObserver.onError(
                    Status.INVALID_ARGUMENT
                        .withDescription("e-mail no formato inválido")
                        .asRuntimeException()
                )



                return responseObserver.onError(
                    Status.INVALID_ARGUMENT
                        .withDescription("o campo deve estar vazio")
                        .asRuntimeException()
                )



        else -> println("nenhuma das anteriores") */

}
