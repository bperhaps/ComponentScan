package com.example.component_scan.scanner;

import static java.util.stream.Collectors.toList;

import com.example.component_scan.annotation.ForLoginAndGuest;
import com.example.component_scan.annotation.ForLoginUser;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class MethodScanner {

    private final List<Class<?>> controllers;

    public MethodScanner(List<Class<?>> controllers) {
        this.controllers = controllers;
    }

    public MethodScanner(Class<?>... controllers) {
        this(List.of(controllers));
    }

    public List<Method> extractAnnotatedMethod() {
        return controllers.stream()
            .flatMap(controller -> Arrays.stream(controller.getMethods()))
            .filter(method ->
                method.isAnnotationPresent(ForLoginAndGuest.class) ||
                    method.isAnnotationPresent(ForLoginUser.class))
            .collect(toList());
    }
}
