package com.diegocardoso.apirestjunit.services.exeptions;

public class ObjectNotFoundExeption extends RuntimeException {

    public ObjectNotFoundExeption(String message) {
        super(message);
    }


}
