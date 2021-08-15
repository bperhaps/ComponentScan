package com.example.component_scan.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.component_scan.scanner.MethodScanner;
import java.lang.reflect.Method;
import java.util.List;
import org.junit.jupiter.api.Test;

class MethodScanTest {

    @Test
    void extractMethod() {
        List<Method> methods = new MethodScanner(
            ScanController1.class,
            ScanController2.class
        ).extractAnnotatedMethod();

        assertThat(methods).hasSize(5);
    }
}
