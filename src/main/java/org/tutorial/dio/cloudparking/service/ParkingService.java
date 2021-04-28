package org.tutorial.dio.cloudparking.service;

import org.springframework.stereotype.Service;
import org.tutorial.dio.cloudparking.model.Parking;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ParkingService {

    private final static Map<String, Parking> mockRepository = new HashMap<>();

    static {
        String id1 = getUUID();
        String id2 = getUUID();
        Parking parking1 = new Parking(id1, "DMS-1111", "SC", "CELTA", "PRETO");
        Parking parking2 = new Parking(id2, "WAS-1234", "SP", "VW GOL", "VERMELHO");
        mockRepository.put(id1, parking1);
        mockRepository.put(id2, parking2);
    }

    public Stream<Parking> findAll() {
        return mockRepository.values().stream();
    }

    public Optional<Parking> findById(String id) {
        return Optional.ofNullable(mockRepository.get(id));
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
