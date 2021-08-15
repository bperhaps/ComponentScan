package com.example.component_scan.controller;

import static java.util.Comparator.comparing;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.component_scan.scanner.ControllerScanner;
import com.example.component_scan.scanner.URIScanner;
import com.example.component_scan.scanner.data_structure.UriAndMethod;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;

class URIScannerTest {

    @Test
    void extractUriAndMethod() {
        List<UriAndMethod> urisAndMethod = new URIScanner(
            new ControllerScanner(
                ScanController1.class,
                ScanController2.class,
                ScanController3.class
            )
        ).extractUriAndMethods();

        List<UriAndMethod> expected = new ArrayList<>(List.of(
            new UriAndMethod("/getTestGuest", HttpMethod.GET),
            new UriAndMethod("/postTstLogin", HttpMethod.POST),
            new UriAndMethod("/controller2/getTestLogin", HttpMethod.GET),
            new UriAndMethod("/controller2/postTestGuest", HttpMethod.POST),
            new UriAndMethod("/controller2/deleteTestLogin", HttpMethod.DELETE)
        ));

        urisAndMethod.sort(comparing(UriAndMethod::getUri).thenComparing(UriAndMethod::getHttpMethod));
        expected.sort(comparing(UriAndMethod::getUri).thenComparing(UriAndMethod::getHttpMethod));

        assertThat(urisAndMethod)
            .usingRecursiveComparison()
            .isEqualTo(expected);
    }
}
