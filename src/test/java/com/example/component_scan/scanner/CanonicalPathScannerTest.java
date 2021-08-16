package com.example.component_scan.scanner;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.component_scan.scanner.test_classes.Test1;
import com.example.component_scan.scanner.test_classes.Test2;
import com.example.component_scan.scanner.test_classes.inner.Test3;
import java.util.List;
import org.junit.jupiter.api.Test;

class CanonicalPathScannerTest {

    @Test
    void getAllCanonicalPaths() {
        CanonicalPathScanner canonicalPathScanner =
            new CanonicalPathScanner("com.example.component_scan.scanner.test_classes");
        List<String> allCanonicalPaths = canonicalPathScanner.getAllCanonicalPaths();

        assertThat(allCanonicalPaths)
            .containsOnly(
                Test1.class.getCanonicalName(),
                Test2.class.getCanonicalName(),
                Test3.class.getCanonicalName()
            );
    }
}
