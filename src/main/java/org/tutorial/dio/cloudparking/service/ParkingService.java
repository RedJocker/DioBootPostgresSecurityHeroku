package org.tutorial.dio.cloudparking.service;

import org.springframework.stereotype.Service;
import org.tutorial.dio.cloudparking.model.Parking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ParkingService {

    private static Map<String, Parking> mockRepository = new HashMap<>();

    static {
        String id = getUUID();
        Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO");
        mockRepository.put(id, parking);
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public List<Parking> findAll(){
        return List.copyOf(mockRepository.values());
    }
}
