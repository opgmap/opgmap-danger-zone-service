package com.example.opgmap_danger_zone_service.exception.handler;

import com.example.opgmap_danger_zone_service.exception.model.EntityNotExistsException;
import com.example.opgmap_danger_zone_service.exception.utils.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({EntityNotExistsException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage returnErrorMessage(EntityNotExistsException ex, WebRequest request) {
        return ErrorMessage.builder()
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
    }
}
