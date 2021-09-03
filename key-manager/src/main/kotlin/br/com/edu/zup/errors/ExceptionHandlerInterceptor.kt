package br.com.edu.zup.errors

import br.com.edu.zup.novaChave.NovaChaveEndpoint
import br.com.edu.zup.shared.handleConstraintViolationException
import br.com.edu.zup.validators.ChavePixException
import io.grpc.Status
import io.grpc.stub.StreamObserver
import io.micronaut.aop.InterceptorBean
import io.micronaut.aop.MethodInterceptor
import io.micronaut.aop.MethodInvocationContext
import org.slf4j.LoggerFactory
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import javax.inject.Singleton
import javax.validation.ConstraintViolationException


@Singleton
@InterceptorBean(ErrorHandler::class)
class ExceptionHandlerInterceptor : MethodInterceptor<NovaChaveEndpoint, Any?> {

    private val LOGGER = LoggerFactory.getLogger(this.javaClass)

    override fun intercept(context: MethodInvocationContext<NovaChaveEndpoint, Any?>): Any? {

        LOGGER.info("ExceptionHandlerInterceptor em ${context.targetMethod}")

        try{
            return context.proceed()
        }catch (e: Exception){

            val statusError = when(e){
                is ChavePixException -> Status.ALREADY_EXISTS.withDescription(e.message).asRuntimeException();
                is IllegalArgumentException -> Status.INVALID_ARGUMENT.withDescription(e.message).asRuntimeException();
                is IllegalStateException -> Status.FAILED_PRECONDITION.withDescription(e.message).asRuntimeException();
                is ConstraintViolationException -> handleConstraintViolationException(e);
                else -> Status.UNKNOWN.withDescription("Erro inesperado").asRuntimeException()
            }

            val responseObserver = context.parameterValues[1] as StreamObserver<*>
            responseObserver.onError(statusError)

            return null

        }



    }

}
