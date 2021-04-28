package org.tutorial.dio.cloudparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class CarInfoInconsistencyPreventionException extends RuntimeException {
    public CarInfoInconsistencyPreventionException(String updateCarInfoId, String parkedCarId) {
        super(String.format(
                "prevented update from info with carId(%s) that didn't match the car to update carId(%s)",
                updateCarInfoId,
                parkedCarId));
    }
}
