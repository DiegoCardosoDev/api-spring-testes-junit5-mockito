package com.diegocardoso.apirestjunit.resources.exeptions;

import com.diegocardoso.apirestjunit.services.exeptions.DataIntegrateViolationExeption;
import com.diegocardoso.apirestjunit.services.exeptions.ObjectNotFoundExeption;
import org.hibernate.ObjectDeletedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class ResourceExeptionHandler {


    @ExceptionHandler(ObjectDeletedException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundExeption ex, HttpServletRequest request) {
        StandardError error =
                new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body((error));

    }

    @ExceptionHandler(DataIntegrateViolationExeption.class)
    public ResponseEntity<StandardError> dataIntegration(DataIntegrateViolationExeption ex, HttpServletRequest request) {
        StandardError error =
                new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((error));

    }


}
