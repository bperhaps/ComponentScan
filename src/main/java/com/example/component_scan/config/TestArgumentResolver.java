package com.example.component_scan.config;

import com.example.component_scan.scanner.data_structure.UriAndMethod;
import java.util.List;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class TestArgumentResolver implements HandlerMethodArgumentResolver {

    private final List<UriAndMethod> uriAndMethodLists;

    public TestArgumentResolver(List<UriAndMethod> uriAndMethodLists) {
        this.uriAndMethodLists = uriAndMethodLists;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(TestResolver.class);
    }

    @Override
    public Object resolveArgument(
        MethodParameter parameter,
        ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory
    ) throws Exception {
        return uriAndMethodLists;
    }
}
