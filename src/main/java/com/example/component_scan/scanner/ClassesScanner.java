package com.example.component_scan.scanner;

import static java.util.stream.Collectors.toList;

import java.util.List;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class ClassesScanner {

    public List<Class<?>> getAllClasses(String basePackage) {
        Reflections reflections = new Reflections(
            basePackage,
            new SubTypesScanner(false)
        );

        return reflections.getSubTypesOf(Object.class)
            .stream()
            .distinct()
            .collect(toList());
    }
}
