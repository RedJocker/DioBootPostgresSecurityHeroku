package org.tutorial.dio.cloudparking.controller.dto;


import org.tutorial.dio.cloudparking.exception.CarInfoInconsistencyPreventionException;
import org.tutorial.dio.cloudparking.model.Parking;

public class FixCarInfoDto {

    public final String license;
    public final String state;
    public final String model;
    public final String color;

    public FixCarInfoDto(String license, String state, String model, String color) {

        this.license = license;
        this.state = state;
        this.model = model;
        this.color = color;
    }

    public Parking updateInfo(Parking parkedCar, String carId){
        if(!carId.equals(parkedCar.getId())) {
            throw new CarInfoInconsistencyPreventionException(carId, parkedCar.getId());
        }
        return new Parking(
                carId,
                license != null ? license : parkedCar.getLicense(),
                state != null ? state : parkedCar.getState(),
                model != null ? model : parkedCar.getModel(),
                color != null ? color : parkedCar.getColor(),
                parkedCar.getEntryDate()
        );
    }
}
