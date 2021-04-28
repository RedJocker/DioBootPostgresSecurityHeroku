package org.tutorial.dio.cloudparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarIdNotFoundException extends RuntimeException {

    public final String id;

    public CarIdNotFoundException(String id) {
        super("Not found Id: " + id);
        this.id = id;
    }
}
