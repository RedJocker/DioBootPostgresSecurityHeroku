package org.tutorial.dio.cloudparking.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.tutorial.dio.cloudparking.model.Parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarCheckoutDto {

    public final String id;
    public final String license;
    public final String state;
    public final String model;
    public final String color;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm") public final LocalDateTime entryDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm") public final LocalDateTime exitDate;
    public final Double bill;

    private  CarCheckoutDto(String id, String license, String state, String model, String color, LocalDateTime entryDate) {
        this.id = id;
        this.license = license;
        this.state = state;
        this.model = model;
        this.color = color;
        this.entryDate = entryDate;
        this.exitDate = LocalDateTime.now();
        this.bill = calculateBill();

    }

    private Double calculateBill() {
        long hours = ChronoUnit.HOURS.between(entryDate, exitDate);
        return 10.0 * hours;
    }


    public static CarCheckoutDto fromParking(Parking parking) {

        return new CarCheckoutDto(
                parking.getId(),
                parking.getLicense(),
                parking.getState(),
                parking.getModel(),
                parking.getColor(),
                parking.getEntryDate()
        );
    }


}
