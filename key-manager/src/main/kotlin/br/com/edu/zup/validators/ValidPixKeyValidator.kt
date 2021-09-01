package br.com.edu.zup.validators

import br.com.edu.zup.novaChave.NovaChaveDTO
import javax.inject.Singleton
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext


@Singleton
class ValidPixKeyValidator: ConstraintValidator<ValidChavePix, NovaChaveDTO> {

    override fun isValid(value: NovaChaveDTO?, context: ConstraintValidatorContext): Boolean {

        if (value?.tipoChave == null){
            return false
        }

        return value.tipoChave.valida(value.valorChave)

    }


}