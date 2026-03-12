package com.lunarvoid.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lunarvoid.dto.exceptions.DTOAException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class DTOAExceptionHandler {

    @ExceptionHandler(DTOAException.class)
    public ResponseEntity<StandardError> resouceError(DTOAException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), "Entrada invalida", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
