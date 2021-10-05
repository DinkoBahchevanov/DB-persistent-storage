package com.example.persistancestorage.exceptions.handling;

import com.example.persistancestorage.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex, HttpServletRequest httpServletRequest) {
        ApiError body = new ApiError(OffsetDateTime.now(), NOT_FOUND.value(), ex.getMessage(), httpServletRequest.getRequestURI());
        return new ResponseEntity<>(body, NOT_FOUND);
    }
}
