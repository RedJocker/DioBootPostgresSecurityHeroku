package org.tutorial.dio.cloudparking.model;

import java.time.LocalDateTime;

public class Parking {

    public final String id;
    public final String license;
    public final String state;
    public final String model;
    public final String color;
    public final LocalDateTime entryDate;

    public Parking(String id, String license, String state, String model, String color, LocalDateTime entryDate) {
        this.id = id;
        this.license = license;
        this.state = state;
        this.model = model;
        this.color = color;
        this.entryDate = entryDate;
    }
}
