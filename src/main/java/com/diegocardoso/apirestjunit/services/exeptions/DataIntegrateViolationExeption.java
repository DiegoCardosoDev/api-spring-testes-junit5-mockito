package com.diegocardoso.apirestjunit.services.exeptions;

public class DataIntegrateViolationExeption extends RuntimeException {

    public DataIntegrateViolationExeption(String message) {
        super(message);
    }


}
