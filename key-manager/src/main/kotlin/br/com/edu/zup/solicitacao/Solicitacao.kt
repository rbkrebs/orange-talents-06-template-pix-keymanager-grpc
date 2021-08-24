package br.com.edu.zup.solicitacao

import br.com.edu.zup.TipoChave
import br.com.edu.zup.TipoConta
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank


@Entity
class Solicitacao(@field:NotBlank val codigoInterno: String,
                  @field:NotBlank val tipoChave: TipoChave,
                  @field:NotBlank val valorChave: String,
                  @field:NotBlank val tipoConta: TipoConta) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
