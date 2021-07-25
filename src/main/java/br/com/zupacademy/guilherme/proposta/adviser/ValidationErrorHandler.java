package br.com.zupacademy.guilherme.proposta.adviser;

import br.com.zupacademy.guilherme.proposta.controller.dto.response.FormErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collection;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public FormErrorDto handle(MethodArgumentNotValidException exception) {
        Collection<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        Collection<String> errors = new ArrayList<>();
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            errors.add(e.getField() + " " + mensagem);
        });
        return new FormErrorDto(errors);
    }


}
