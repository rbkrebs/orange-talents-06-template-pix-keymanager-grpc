package br.com.edu.zup.pix

import br.com.edu.zup.*
import io.grpc.Status
import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PixEndpoint(@Inject val pixRepository: PixRepository) : KeyManagerServiceGrpc.KeyManagerServiceImplBase(){

    private val logger = LoggerFactory.getLogger(PixEndpoint::class.java)

    override fun registraChavePix(
        request: SolicitacaoRequest,
        responseObserver: StreamObserver<SolicitacaoResponse>
    ) {
        logger.info("Cadastrando pix $request")

        if(pixRepository.findByCodigo(request.codigoInterno).isPresent){

            return responseObserver.onError(
                Status.ALREADY_EXISTS
                    .withDescription("PIX já cadastrado")
                    .asRuntimeException()
            )

        }


        var valorDocument : String = "";

        val documento = request.tipoChave
        when(documento){
            TipoChave.CPF -> {
                if(!request.valorChave.matches("^[0-9]{11}\$".toRegex())){

                    return responseObserver.onError(Status.INVALID_ARGUMENT
                                .withDescription("formato de cpf inválido")
                                .augmentDescription("formato esperado é 12345678901")
                                .asRuntimeException())
                }
                valorDocument = request.valorChave;

            }
            TipoChave.TELEFONE_CELULAR -> {
                if (!request.valorChave.matches("^\\+[1-9][0-9]\\d{1,14}\$".toRegex())) {
                    return responseObserver.onError(
                        Status.INVALID_ARGUMENT
                            .withDescription("formato de celular errado")
                            .augmentDescription("formato esperado é +5585988714077")
                            .asRuntimeException()
                    )

                }
                valorDocument = request.valorChave;

            }
            TipoChave.EMAIL -> {
                if (!request.valorChave.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex())) {
                    return responseObserver.onError(
                        Status.INVALID_ARGUMENT
                            .withDescription("e-mail no formato inválido")
                            .asRuntimeException()
                    )

                }
                valorDocument = request.valorChave;

            }
            TipoChave.CHAVE_ALEATORIA -> {
                if (request.valorChave.isNotEmpty()) {
                    return responseObserver.onError(
                        Status.INVALID_ARGUMENT
                            .withDescription("o campo deve estar vazio")
                            .asRuntimeException()
                    )

                }
                valorDocument = UUID.randomUUID().toString();

            }
            else -> println("nenhuma das anteriores")
        }

        val solicitacaoModel = request.toModel(valorDocument)

        val solicitacaoResponse  =  pixRepository.save(solicitacaoModel)
        val texto = "Romulo"
        var response = SolicitacaoResponse.newBuilder().setCodigoInterno(solicitacaoResponse.id.toString()).build();

        responseObserver.onNext(response)
        responseObserver.onCompleted();
    }
}

fun SolicitacaoRequest.toModel(valorDocument : String) : Solicitacao {

    return Solicitacao(this.codigoInterno,
    this.tipoChave,
        valorDocument,
    this.tipoConta)

}