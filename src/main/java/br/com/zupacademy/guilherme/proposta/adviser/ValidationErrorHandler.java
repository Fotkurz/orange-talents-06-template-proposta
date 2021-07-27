package br.com.zupacademy.guilherme.proposta.adviser;

import br.com.zupacademy.guilherme.proposta.controller.dto.response.FormErrorDto;
import br.com.zupacademy.guilherme.proposta.exception.ApiErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public FormErrorDto handle(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        Collection<String> messages = new ArrayList<>();
        fieldErrors.forEach(e -> {
            String campo = e.getField();
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            messages.add("Field: " + campo + " " + mensagem);
        });

        return new FormErrorDto(messages);
    }

    @ExceptionHandler(ApiErrorException.class)
    public ResponseEntity<FormErrorDto> handler(ApiErrorException exception) {
        Collection<String> messages = new ArrayList<>();
        messages.add(exception.getReason());

        FormErrorDto formErrorDto = new FormErrorDto(messages);
        return ResponseEntity.status(exception.getStatus()).body(formErrorDto);
    }
}
