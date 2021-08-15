package com.example.component_scan.controller;

import static java.util.Comparator.comparing;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.component_scan.scanner.data_structure.UriAndMethod;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void getUriAndMethods() {
        List<UriAndMethod> uriAndMethods = RestAssured.given().log().all()
            .when().log().all()
            .get("/getUriAndMethods")
            .then().log().all()
            .extract()
            .as(new TypeRef<>() {
            });

        List<UriAndMethod> expected = new ArrayList<>(List.of(
            new UriAndMethod("/getTestGuest", HttpMethod.GET),
            new UriAndMethod("/postTstLogin", HttpMethod.POST),
            new UriAndMethod("/controller2/getTestLogin", HttpMethod.GET),
            new UriAndMethod("/controller2/postTestGuest", HttpMethod.POST),
            new UriAndMethod("/controller2/deleteTestLogin", HttpMethod.DELETE)
        ));

        uriAndMethods.sort(comparing(UriAndMethod::getUri).thenComparing(UriAndMethod::getHttpMethod));
        expected.sort(comparing(UriAndMethod::getUri).thenComparing(UriAndMethod::getHttpMethod));

        assertThat(uriAndMethods)
            .usingRecursiveComparison()
            .isEqualTo(expected);
    }
}
