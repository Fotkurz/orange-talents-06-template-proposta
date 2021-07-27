package br.com.zupacademy.guilherme.proposta.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = ExistsIdValidator.class)
public @interface ExistId {
    String fieldName();
    Class<?> clazz();
    String message() default "unique constraint validation failed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
