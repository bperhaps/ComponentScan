package com.example.component_scan.scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.component_scan.controller.ScanController1;
import com.example.component_scan.controller.ScanController2;
import com.example.component_scan.controller.ScanController3;
import java.util.List;
import org.junit.jupiter.api.Test;

class ClassesScannerTest {

    @Test
    void getAllClasses() {
        List<Class<?>> allClasses =
            new ClassesScanner()
                .getAllClasses("com.example.component_scan.controller");

        assertThat(allClasses)
            .containsOnly(
                ScanController1.class,
                ScanController2.class,
                ScanController3.class
            );
    }
}
