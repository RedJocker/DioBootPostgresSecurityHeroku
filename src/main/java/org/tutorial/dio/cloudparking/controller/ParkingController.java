package org.tutorial.dio.cloudparking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tutorial.dio.cloudparking.model.Parking;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @GetMapping
    public List<Parking> findAll() {
         var parking =  new Parking();
         parking.setColor("PRETO");
         parking.setLicense("MSS-1111");
         parking.setModel("VW  GOL");
         parking.setState("SP");

         return List.of(parking, parking);

    }
}
