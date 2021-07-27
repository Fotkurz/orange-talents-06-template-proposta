package br.com.zupacademy.guilherme.proposta.validation;

import br.com.zupacademy.guilherme.proposta.exception.ApiErrorException;
import org.springframework.http.HttpStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.Serializable;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistId, Serializable> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> clazz;
    private String fieldName;

    @Override
    public void initialize(ExistId constraintAnnotation) {
        this.clazz = constraintAnnotation.clazz();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Serializable value, ConstraintValidatorContext context) {
        List list =  entityManager.createQuery("SELECT x FROM " + clazz.getSimpleName() + " x WHERE x." + fieldName + " = :pValue")
                .setParameter("pValue", value).getResultList();
        if(list.isEmpty()) {
            throw new ApiErrorException(HttpStatus.NOT_FOUND, fieldName + " not found");
        }
        return true;
    }
}
