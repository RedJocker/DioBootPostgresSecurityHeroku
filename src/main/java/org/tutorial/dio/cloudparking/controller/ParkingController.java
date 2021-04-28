package org.tutorial.dio.cloudparking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tutorial.dio.cloudparking.controller.dto.ParkingDTO;
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
    public ResponseEntity<List<ParkingDTO>> findAll() {

        return parkingService.findAll()
                .map(ParkingDTO::fromParking)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        ResponseEntity::ok
                ));
    }


    @GetMapping("{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable("id") String id) {

        return parkingService.findById(id)
                .map(ParkingDTO::fromParking)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }
}
