package br.com.edu.zup.pix

import br.com.edu.zup.*
import br.com.edu.zup.solicitacao.Solicitacao
import io.grpc.Status
import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory
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

        val solicitacaoModel = request.toModel()
        val documento = request.tipoChave
        when(documento){
            TipoChave.CPF -> {
                if(!request.valorChave.matches("/^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}\$/".toRegex())){
                            responseObserver?.onError(Status.INVALID_ARGUMENT
                                .withDescription("formato de cpf inválido")
                                .augmentDescription("formato esperado é 111.111.111-11")
                                .asRuntimeException())
                }
            }
            TipoChave.TELEFONE_CELULAR -> println("2")
            TipoChave.EMAIL -> println("3")
            TipoChave.CHAVE_ALEATORIA -> println("4")
            else -> println("nenhuma das anteriores")

        }

        // testa conexão banco e persistência
        //var pix = Pix("teste")
        //pixRepository.save(pix)

        var response = SolicitacaoResponse.newBuilder().setCodigoInterno("ROMULO").build();

        responseObserver?.onNext(response)
        responseObserver?.onCompleted();
    }
}

fun SolicitacaoRequest.toModel() : Solicitacao {

    return Solicitacao(this.codigoInterno,
    this.tipoChave,
    this.valorChave,
    this.tipoConta)

}