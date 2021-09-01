package br.com.edu.zup.novaChave

import br.com.edu.zup.clients.ContaCliente
import java.util.*
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


@Entity
@Table(uniqueConstraints = [UniqueConstraint(name = "uk_chave_pix",
                                            columnNames = ["valorChave"])])
class NovaChave(
    @field:NotNull
    @Column(nullable = false)
    val clienteId: UUID,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipoChave: TipoChave,

    @field:NotBlank
    @Column(unique = true, nullable = false)
    val valorChave: String?,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipoConta: TipoConta,

    @field:Valid
    @Embedded
    val conta: ContaCliente)
{





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null


}
