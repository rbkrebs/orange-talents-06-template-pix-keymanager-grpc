package br.com.edu.zup.novaChave

import br.com.edu.zup.*
import br.com.edu.zup.shared.toModel
import br.com.edu.zup.errors.ErrorHandler
import br.com.edu.zup.grpc.KeyManagerServiceGrpc
import br.com.edu.zup.grpc.SolicitacaoRequest
import br.com.edu.zup.grpc.SolicitacaoResponse
import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Singleton


@ErrorHandler
@Singleton
open class NovaChaveEndpoint(@Inject private val service: NovaChaveService) : KeyManagerServiceGrpc.KeyManagerServiceImplBase(){

    private val LOGGER = LoggerFactory.getLogger(NovaChaveEndpoint::class.java)

    open override fun registraChavePix(
        request: SolicitacaoRequest,
        responseObserver: StreamObserver<SolicitacaoResponse>
    ) {
        LOGGER.info("Cadastrando pix $request")

        val novaChaveDTO= request.toModel()
        val chavePixCriada = service.registra(novaChaveDTO)
        var response = SolicitacaoResponse.newBuilder().setPixId(chavePixCriada.valorChave).build();


        responseObserver.onNext(response)
        responseObserver.onCompleted();
    }
}

