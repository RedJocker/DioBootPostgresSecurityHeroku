package org.tutorial.dio.cloudparking.service;

import org.springframework.stereotype.Service;
import org.tutorial.dio.cloudparking.controller.dto.IngressingCarDto;
import org.tutorial.dio.cloudparking.model.Parking;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ParkingService {

    private final static Map<String, Parking> mockRepository = new HashMap<>();

    static {
        Stream.of(
                new IngressingCarDto("DMS-1111", "SC", "CELTA", "PRETO").toParking(),
                new IngressingCarDto("WAS-1234", "SP", "VW GOL", "VERMELHO").toParking()
        ).forEach(parkingCar -> mockRepository.put(parkingCar.id, parkingCar));

    }

    public Stream<Parking> findAll() {
        return mockRepository.values().stream();
    }

    public Optional<Parking> findById(String id) {
        return Optional.ofNullable(mockRepository.get(id));
    }

    public Parking create (Parking parkingCreate) {
        mockRepository.put(parkingCreate.id, parkingCreate);
        return parkingCreate;
    }


    public Optional<Parking> checkoutCarById(String carId) {
        final Optional<Parking> maybeCarFound = findById(carId);
        maybeCarFound.ifPresent(car -> mockRepository.remove(carId));
        return maybeCarFound;
    }
}
