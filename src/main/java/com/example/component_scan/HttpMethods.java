package com.example.component_scan;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Function;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public enum HttpMethods {
    GET(GetMapping.class, HttpMethod.GET,
        method -> List.of(method.getAnnotation(GetMapping.class).value())),
    POST(PostMapping.class, HttpMethod.POST,
        method -> List.of(method.getAnnotation(PostMapping.class).value())),
    DELETE(DeleteMapping.class, HttpMethod.DELETE,
        method -> List.of(method.getAnnotation(DeleteMapping.class).value())),
    PUT(PutMapping.class, HttpMethod.PUT,
        method -> List.of(method.getAnnotation(PutMapping.class).value()));

    private final Class<? extends Annotation> typeToken;
    private final HttpMethod httpMethod;
    private final Function<Method, List<String>> values;

    HttpMethods(
        Class<? extends Annotation> typeToken,
        HttpMethod httpMethod,
        Function<Method, List<String>> values
    ) {
        this.typeToken = typeToken;
        this.httpMethod = httpMethod;
        this.values = values;
    }

    public static List<String> getUrisIfExist(Method method) {
        return List.of(values()).stream()
            .filter(httpMethod -> method.isAnnotationPresent(httpMethod.typeToken))
            .map(httpMethods -> httpMethods.values.apply(method))
            .findAny()
            .orElse(List.of(""));
    }

    public static HttpMethod getHttpMethod(Method method) {
        return List.of(values()).stream()
            .filter(httpMethod -> method.isAnnotationPresent(httpMethod.typeToken))
            .map(httpMethod -> httpMethod.httpMethod)
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }
}
