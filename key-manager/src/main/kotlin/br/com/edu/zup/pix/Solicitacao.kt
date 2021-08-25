package br.com.edu.zup.pix

import br.com.edu.zup.TipoChave
import br.com.edu.zup.TipoConta
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


@Entity
class Solicitacao(@field:NotBlank val codigo: String,
                  @field:NotNull val tipoChave: TipoChave,
                  @field:NotBlank val valorChave: String,
                  @field:NotNull val tipoConta: TipoConta) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
