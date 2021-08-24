package br.com.edu.zup

import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client
import java.net.http.HttpResponse


@Client(value = "localhost:9091/api/v1/clientes/clienteId")
interface InstFinanceiraClient {

    @Get
    fun consultaId(@QueryValue clienteId:String) :HttpResponse<String>
}