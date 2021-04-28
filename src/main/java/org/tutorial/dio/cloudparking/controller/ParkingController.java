package org.tutorial.dio.cloudparking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tutorial.dio.cloudparking.controller.dto.ParkingPostRequestDTO;
import org.tutorial.dio.cloudparking.controller.dto.ParkingResponseDTO;
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
    public ResponseEntity<List<ParkingResponseDTO>> findAll() {

        return parkingService.findAll()
                .map(ParkingResponseDTO::fromParking)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        ResponseEntity::ok
                ));
    }


    @GetMapping("{id}")
    public ResponseEntity<ParkingResponseDTO> findById(@PathVariable("id") String id) {

        return parkingService.findById(id)
                .map(ParkingResponseDTO::fromParking)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @PostMapping
    public ResponseEntity<ParkingResponseDTO> create(@RequestBody ParkingPostRequestDTO creationDto) {
        final Parking created = parkingService.create(creationDto.toParking());
        return ResponseEntity.status(HttpStatus.CREATED).body(ParkingResponseDTO.fromParking(created));
    }
}
