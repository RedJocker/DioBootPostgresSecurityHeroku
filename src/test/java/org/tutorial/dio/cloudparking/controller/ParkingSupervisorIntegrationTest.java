package org.tutorial.dio.cloudparking.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.tutorial.dio.cloudparking.controller.dto.IngressingCarDto;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingSupervisorIntegrationTest {

    private final static String BASE_URI = "/parking";

    @LocalServerPort
    private int randomPort;


    @BeforeEach
    void setUp() {
        RestAssured.port = randomPort;
    }

    @Test
    void listAllCars_whenGetThenStatusOK() {
        RestAssured.given()
                .when()
                .get(BASE_URI)
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_VALUE);



    }

    @Test
    void carEntry_whenValidBodyThenStatusCreated() {

        IngressingCarDto ingressingCarDto = new IngressingCarDto(
               "AAA-1111",
                "SP",
                "GOL",
                "BLACK"
        );

        RestAssured.given()
                .when()
                .body(ingressingCarDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .post(BASE_URI)
                .then()
                .statusCode(HttpStatus.CREATED.value());


    }


}