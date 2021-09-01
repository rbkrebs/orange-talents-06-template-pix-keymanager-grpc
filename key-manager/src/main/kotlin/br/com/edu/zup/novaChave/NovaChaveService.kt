package br.com.edu.zup.novaChave


import br.com.edu.zup.clients.InstFinanceiraClient
import br.com.edu.zup.validators.ChavePixException
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Singleton
class NovaChaveService (@Inject val repository: NovaChaveRepository,
                        @Inject val instFinanceiraClient: InstFinanceiraClient){

    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun registra(@Valid novaChave: NovaChaveDTO): NovaChave{

        if(repository.existsByValorChave(novaChave.valorChave)){
            throw ChavePixException("Chave '${novaChave.valorChave}' já cadastrada")
        }

        val response = instFinanceiraClient.buscaContaPorTipo(novaChave.clienteId!!, novaChave.tipoConta!!.name)
        val conta = response.body()?.toModel() ?: throw IllegalStateException("Cliente não encontrado")

        val chave = novaChave.toModel(conta)
        repository.save(chave)

        return chave

    }

}