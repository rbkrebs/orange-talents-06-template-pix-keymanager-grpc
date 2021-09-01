package br.com.edu.zup.clients

import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client
import java.net.http.HttpResponse


@Client(value = "\${itau_client.contas.url}")
interface InstFinanceiraClient {



    @Get("/api/v1/clientes/{clienteId}/contas")
    fun buscaContaPorTipo(@PathVariable clienteId: String, @QueryValue tipo: String):HttpResponse<ContaClienteResponse>
}