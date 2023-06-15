package com.company.urlshortener.exception;

import com.company.urlshortener.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleException(Exception exc) {
        ErrorDTO errorDTO = new ErrorDTO(exc.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorDTO> handleException(CommonException exc) {
        return new ResponseEntity<>(new ErrorDTO(exc.getMessage()), exc.getStatus());
    }
}
