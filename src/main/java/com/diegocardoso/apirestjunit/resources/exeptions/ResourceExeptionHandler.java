package com.diegocardoso.apirestjunit.resources.exeptions;

import org.hibernate.ObjectDeletedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class ResourceExeptionHandler {

    
    @ExceptionHandler(ObjectDeletedException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectDeletedException ex, HttpServletRequest request) {
        StandardError error =
                new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body((error));

    }
}
