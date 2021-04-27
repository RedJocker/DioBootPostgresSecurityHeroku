package org.tutorial.dio.cloudparking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tutorial.dio.cloudparking.controller.dto.ParkingDTO;
import org.tutorial.dio.cloudparking.model.Parking;
import org.tutorial.dio.cloudparking.service.ParkingService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/parking")
public class ParkingController {


    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping
    public List<ParkingDTO> findAll() {
        return parkingService.findAll().stream().map(ParkingDTO::fromParking).collect(Collectors.toList());

    }
}
