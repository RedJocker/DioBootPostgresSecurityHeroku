package org.tutorial.dio.cloudparking.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.tutorial.dio.cloudparking.model.Parking;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDetailsDto {

    public final String id;
    public final String license;
    public final String state;
    public final String model;
    public final String color;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm") public final LocalDateTime entryDate;


    public CarDetailsDto(String id, String license, String state, String model, String color, LocalDateTime entryDate) {
        this.id = id;
        this.license = license;
        this.state = state;
        this.model = model;
        this.color = color;
        this.entryDate = entryDate;

    }

    public static CarDetailsDto fromParking(Parking parking) {
        return new CarDetailsDto(
                parking.getId(),
                parking.getLicense(),
                parking.getState(),
                parking.getModel(),
                parking.getColor(),
                parking.getEntryDate()
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
                null,
                null
        );
    }


}
