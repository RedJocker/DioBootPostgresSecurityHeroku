package org.tutorial.dio.cloudparking.service;

import org.springframework.stereotype.Service;
import org.tutorial.dio.cloudparking.controller.dto.FixCarInfoDto;
import org.tutorial.dio.cloudparking.controller.dto.IngressingCarDto;
import org.tutorial.dio.cloudparking.model.Parking;
import org.tutorial.dio.cloudparking.repository.ParkingRepository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ParkingService {

    private final static Map<String, Parking> mockRepository = new HashMap<>();
    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @PostConstruct
    public void setUpMockData() {

        Stream.of(
                new IngressingCarDto("DMS-1111", "SC", "CELTA", "PRETO").toParking(),
                new IngressingCarDto("WAS-1234", "SP", "VW GOL", "VERMELHO").toParking()
        ).forEach(parkingRepository::save);
    }


    public Stream<Parking> findAll() {
        return parkingRepository.findAll().stream();
    }

    public Optional<Parking> findById(String id) {
        return parkingRepository.findById(id);
    }

    public Parking registerParkingCar(Parking parkingCar) {
        return parkingRepository.save(parkingCar);
    }


    public Optional<Parking> checkoutCarById(String carId) {
        final Optional<Parking> maybeCarFound = findById(carId);
        maybeCarFound.ifPresent(parkingRepository::delete);
        return maybeCarFound;
    }

    public Optional<Parking> updateInfo(String carId, FixCarInfoDto infoDto) {
        final Optional<Parking> maybeParkingUpdated = findById(carId)
                    .map(foundCar -> infoDto.updateInfo(foundCar, carId));

        maybeParkingUpdated.ifPresent(parkingRepository::save);

        return maybeParkingUpdated;

    }
}
