package com.example.component_scan.scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.component_scan.controller.ScanController1;
import com.example.component_scan.controller.ScanController2;
import com.example.component_scan.controller.ScanController3;
import com.example.component_scan.scanner.test_classes.Test1;
import com.example.component_scan.scanner.test_classes.Test2;
import com.example.component_scan.scanner.test_classes.inner.Test3;
import java.util.List;
import org.junit.jupiter.api.Test;

class ClassesScannerTest {

    @Test
    void getAllClasses() {
        List<Class<?>> allClasses =
            new ClassesScanner(
                new CanonicalPathScanner("com.example.component_scan.scanner.test_classes")
            ).getAllClasses();

        assertThat(allClasses)
            .containsOnly(
                Test1.class,
                Test2.class,
                Test3.class
            );
    }
}
