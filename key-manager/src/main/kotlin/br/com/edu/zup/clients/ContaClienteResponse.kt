package br.com.edu.zup.clients

data class ContaClienteResponse(

    val tipo: String,
    val instituicaoResponse: InstituicaoResponse,
    val agencia: String,
    val numero: String,
    val titular: ClienteResponse) {

    fun toModel(): ContaCliente {

        return ContaCliente(

            instituicao = this.instituicaoResponse.nome,
            nomeTitular = this.titular.nome,
            cpfTitular = this.titular.cpf,
            agencia = this.agencia,
            numeroConta = this.numero


        )

    }

}
