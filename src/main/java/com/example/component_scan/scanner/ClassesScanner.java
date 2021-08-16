package com.example.component_scan.scanner;

import java.util.ArrayList;
import java.util.List;

public class ClassesScanner {

    private final CanonicalPathScanner canonicalPathScanner;

    public ClassesScanner(CanonicalPathScanner canonicalPathScanner) {
        this.canonicalPathScanner = canonicalPathScanner;
    }

    public List<Class<?>> getAllClasses() {
        try {
            List<Class<?>> classes = new ArrayList<>();
            List<String> canonicalPaths = canonicalPathScanner.getAllCanonicalPaths();

            for (String canonicalPath : canonicalPaths) {
                classes.add(Class.forName(canonicalPath));
            }

            return classes;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
