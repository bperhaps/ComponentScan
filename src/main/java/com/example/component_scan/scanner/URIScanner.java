package com.example.component_scan.scanner;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import com.example.component_scan.HttpMethods;
import com.example.component_scan.scanner.data_structure.UriAndMethod;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;

public class URIScanner {
    private final ControllerScanner controllerScanner;

    public URIScanner(
        ControllerScanner controllerScanner
    ) {
        this.controllerScanner = controllerScanner;
    }

    public List<UriAndMethod> extractUriAndMethods() {
        List<Class<?>> controllers = controllerScanner.extractController();
        List<UriAndMethod> uriAndMethods =  new ArrayList<>();

        for (Class<?> controller : controllers) {
            List<String> controllerUris = extractControllerUri(controller);
            List<UriAndMethod> methodUris = extractMethodUrisAndHttpMethod(controller);

            uriAndMethods.addAll(mergeUris(controllerUris, methodUris));
        }

        return uriAndMethods;
    }

    private List<UriAndMethod> extractMethodUrisAndHttpMethod(Class<?> controller) {
        List<Method> methods = extractMethods(controller);

        return methods.stream()
            .flatMap(method -> createUriAndMethod(method).stream())
            .collect(toList());
    }

    private List<UriAndMethod> createUriAndMethod(Method method) {
        HttpMethod httpMethod = HttpMethods.getHttpMethod(method);
        return HttpMethods.getUrisIfExist(method).stream()
            .map(uri -> new UriAndMethod(uri, httpMethod))
            .collect(toList());
    }

    private List<Method> extractMethods(Class<?> controller) {
        List<Method> methods = new MethodScanner(controller).extractAnnotatedMethod();
        return methods;
    }

    private List<UriAndMethod> mergeUris(List<String> controllerUris, List<UriAndMethod> uriAndMethods) {
        List<UriAndMethod> uris = new ArrayList<>();

        for (String controllerUri : controllerUris) {
            for (UriAndMethod uriAndMethod : uriAndMethods) {
                uris.add(new UriAndMethod(
                    createUri(controllerUri, uriAndMethod.getUri()),
                    uriAndMethod.getHttpMethod())
                );
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
