package com.example.component_scan.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.component_scan.scanner.ControllerScanner;
import com.example.component_scan.scanner.URIScanner;
import java.util.List;
import org.junit.jupiter.api.Test;

public class URIScannerTest {

    @Test
    void extractUri() {
        List<String> uris = new URIScanner(
            new ControllerScanner(
                ScanController1.class,
                ScanController2.class,
                ScanController3.class
            )
        ).extractUris();

        assertThat(uris).containsOnly(
            "/getTestGuest",
            "/postTstLogin",
            "/controller2/getTestLogin",
            "/controller2/postTestGuest",
            "/controller2/deleteTestLogin"
        );
    }
}
