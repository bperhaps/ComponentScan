package com.example.component_scan.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.component_scan.scanner.ControllerScanner;
import java.util.List;
import org.junit.jupiter.api.Test;

class ControllerScanTest {

    @Test
    void scan() {
        List<Class<?>> controllers = new ControllerScanner(
            ScanController1.class,
            ScanController2.class,
            ScanController3.class
        ).extractController();

        assertThat(controllers).containsOnly(
            ScanController1.class,
            ScanController2.class
        );
    }
}
