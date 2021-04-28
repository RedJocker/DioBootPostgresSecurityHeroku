package org.tutorial.dio.cloudparking.controller.dto;

import org.tutorial.dio.cloudparking.model.Parking;

import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingPostRequestDTO {

    public final String license;
    public final String state;
    public final String model;
    public final String color;

    public ParkingPostRequestDTO(String license, String state, String model, String color) {
        this.license = license;
        this.state = state;
        this.model = model;
        this.color = color;
    }

    public Parking toParking(){

        final String id = getUUID();
        final LocalDateTime now = LocalDateTime.now();
        return new Parking(
                id,
                license,
                state,
                model,
                color,
                now,
                null,
                null
        );

    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
