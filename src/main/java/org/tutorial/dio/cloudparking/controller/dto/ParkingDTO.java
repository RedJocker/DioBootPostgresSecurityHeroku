package org.tutorial.dio.cloudparking.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.tutorial.dio.cloudparking.model.Parking;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParkingDTO {

    public final String id;
    public final String license;
    public final String state;
    public final String model;
    public final String color;
    public final LocalDateTime entryDate;
    public final LocalDateTime exitDate;
    public final Double bill;

    public ParkingDTO(String id, String license, String state, String model, String color, LocalDateTime entryDate, LocalDateTime exitDate, Double bill) {
        this.id = id;
        this.license = license;
        this.state = state;
        this.model = model;
        this.color = color;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.bill = bill;
    }

    public static ParkingDTO fromParking(Parking parking) {
        return new ParkingDTO(
                parking.getId(),
                parking.getLicense(),
                parking.getState(),
                parking.getModel(),
                parking.getColor(),
                parking.getEntryDate(),
                parking.getExitDate(),
                parking.getBill()
        );
    }

    public Parking toParking(){
        return new Parking(
                id,
                license,
                state,
                model,
                color,
                entryDate,
                exitDate,
                bill
        );
    }


}
