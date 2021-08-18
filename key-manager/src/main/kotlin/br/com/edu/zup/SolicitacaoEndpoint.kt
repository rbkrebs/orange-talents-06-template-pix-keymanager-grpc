package br.com.edu.zup

import io.grpc.stub.StreamObserver
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class SolicitacaoEndpoint : KeyManagerServiceGrpc.KeyManagerServiceImplBase(){

    private val logger = LoggerFactory.getLogger(SolicitacaoEndpoint::class.java)

    override fun registraChavePix(
        request: SolicitacaoRequest?,
        responseObserver: StreamObserver<SolicitacaoResponse>?
    ) {
        logger.info("Cadastrando pix $request")
        var response = SolicitacaoResponse.newBuilder().setSolicita(request?.solicita).build();
        responseObserver?.onNext(response)
        responseObserver?.onCompleted();
    }
}