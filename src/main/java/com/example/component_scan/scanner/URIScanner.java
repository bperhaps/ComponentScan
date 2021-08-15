package com.example.component_scan.scanner;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import com.example.component_scan.HttpMethods;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.RequestMapping;

public class URIScanner {
    private final ControllerScanner controllerScanner;

    public URIScanner(
        ControllerScanner controllerScanner
    ) {
        this.controllerScanner = controllerScanner;
    }

    public List<String> extractUris() {
        List<Class<?>> controllers = controllerScanner.extractController();
        List<String> uris =  new ArrayList<>();

        for (Class<?> controller : controllers) {
            List<String> controllerUris = extractControllerUri(controller);
            List<String> methodUris = extractMethodUris(controller);

            uris.addAll(extractUris(controllerUris, methodUris));
        }

        return uris;
    }

    private List<String> extractMethodUris(Class<?> controller) {
        List<Method> methods = new MethodScanner(controller).extractAnnotatedMethod();

        return methods.stream()
            .flatMap(method -> HttpMethods.getUrisIfExist(method).stream())
            .collect(toList());
    }

    private List<String> extractUris(List<String> controllerUris, List<String> methodUris) {
        List<String> uris = new ArrayList<>();
        for (String controllerUri : controllerUris) {
            for (String methodUri : methodUris) {
                uris.add(createUri(controllerUri, methodUri));
            }
        }
        return uris;
    }

    private String createUri(String controllerUri, String methodUri) {
        return "/" + Stream.of(controllerUri.split("/"), methodUri.split("/"))
            .flatMap(Arrays::stream)
            .filter(uriPiece -> !uriPiece.isBlank())
            .collect(joining("/"));

    }

    private List<String> extractControllerUri(Class<?> controller) {
        if(controller.isAnnotationPresent(RequestMapping.class)) {
            return List.of(controller.getAnnotation(RequestMapping.class).value());
        }

        return List.of("");
    }
}
