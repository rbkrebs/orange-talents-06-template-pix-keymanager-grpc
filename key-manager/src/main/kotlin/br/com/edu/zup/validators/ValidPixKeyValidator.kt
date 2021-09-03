package br.com.edu.zup.validators

import br.com.edu.zup.novaChave.NovaChaveDTO
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import javax.inject.Singleton
import javax.validation.ConstraintValidatorContext


@Singleton
class ValidPixKeyValidator: ConstraintValidator<ValidChavePix, NovaChaveDTO> {

    override fun isValid(
        value: NovaChaveDTO?,
        annotationMetadata: AnnotationValue<ValidChavePix>,
        context: io.micronaut.validation.validator.constraints.ConstraintValidatorContext
    ): Boolean {
        if (value?.tipoChave == null){
            return false
        }

        return value.tipoChave.valida(value.valorChave)
    }


}