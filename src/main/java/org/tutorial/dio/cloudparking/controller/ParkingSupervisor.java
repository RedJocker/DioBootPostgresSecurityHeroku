package org.tutorial.dio.cloudparking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tutorial.dio.cloudparking.controller.dto.CarCheckoutDto;
import org.tutorial.dio.cloudparking.controller.dto.CarDetailsDto;
import org.tutorial.dio.cloudparking.controller.dto.IngressingCarDto;
import org.tutorial.dio.cloudparking.exception.CarIdNotFoundException;
import org.tutorial.dio.cloudparking.model.Parking;
import org.tutorial.dio.cloudparking.service.ParkingService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Api(tags = "PARKING SUPERVISOR, the best controller for your parking business")
@RequestMapping("/parking")
public class ParkingSupervisor {


    private final ParkingService parkingService;

    public ParkingSupervisor(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping
    @ApiOperation("List all cars currently in the parking")
    public ResponseEntity<List<CarDetailsDto>> listAllCars() {

        return parkingService.findAll()
                .map(CarDetailsDto::fromParking)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        ResponseEntity::ok
                ));
    }


    @GetMapping("/{carId}")
    @ApiOperation("Find parked cars by carId")
    public ResponseEntity<CarDetailsDto> findCarById(@PathVariable("carId") String carId) {

        return parkingService.findById(carId)
                .map(CarDetailsDto::fromParking)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CarIdNotFoundException(carId));
    }

    @PostMapping
    @ApiOperation("register the incoming of a new car into the parking")
    public ResponseEntity<CarDetailsDto> carEntry(@RequestBody IngressingCarDto incomingCar) {
        return Optional.of(parkingService.create(incomingCar.toParking()))
                    .map(registeredCar -> ResponseEntity.status(HttpStatus.CREATED)
                                                    .body(CarDetailsDto.fromParking(registeredCar)))
                    .get();
    }
    
    @DeleteMapping("/{carId}")
    @ApiOperation("bill a car checking out of the parking")
    public ResponseEntity<CarCheckoutDto> carCheckout(@PathVariable("carId") String carId) {


        final Optional<Parking> maybeFound = parkingService.checkoutCarById(carId);


        return maybeFound
                .map(CarCheckoutDto::fromParking)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CarIdNotFoundException(carId));
    }


    
}
