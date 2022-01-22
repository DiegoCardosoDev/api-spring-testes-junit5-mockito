package com.diegocardoso.apirestjunit.services.exeptions;

public class DataIntegrityViolationExeption extends RuntimeException {

    public DataIntegrityViolationExeption(String message) {
        super(message);
    }


}
