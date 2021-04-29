package org.tutorial.dio.cloudparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tutorial.dio.cloudparking.model.Parking;

public interface ParkingRepository extends JpaRepository<Parking, String> {
}
