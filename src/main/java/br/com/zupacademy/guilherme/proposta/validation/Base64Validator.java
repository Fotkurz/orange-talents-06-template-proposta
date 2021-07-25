package br.com.zupacademy.guilherme.proposta.validation;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Base64Validator implements ConstraintValidator<isBase64, String> {
    @Override
    public void initialize(isBase64 constraintAnnotation) {
    }
    // Terminar de arrumar
    @Override
    public boolean isValid(String base64, ConstraintValidatorContext context) {
        return Base64.isBase64(base64);
    }
}
