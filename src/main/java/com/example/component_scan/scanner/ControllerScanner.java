package com.example.component_scan.scanner;

import static java.util.stream.Collectors.toList;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

public class ControllerScanner {
    private final List<Class<?>> classes;

    public ControllerScanner(List<Class<?>> classes) {
        this.classes = classes;
    }

    public ControllerScanner(Class<?>... classes) {
        this(List.of(classes));
    }

    public List<Class<?>>  extractController() {
        return classes.stream()
            .filter(this::isController)
            .collect(toList());
    }

    private boolean isController(Class<?> typeToken) {
        return typeToken.isAnnotationPresent(Controller.class) ||
            typeToken.isAnnotationPresent(RestController.class);
    }
}
