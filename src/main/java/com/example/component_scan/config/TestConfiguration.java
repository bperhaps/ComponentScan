package com.example.component_scan.config;

import com.example.component_scan.scanner.CanonicalPathScanner;
import com.example.component_scan.scanner.ClassesScanner;
import com.example.component_scan.scanner.ControllerScanner;
import com.example.component_scan.scanner.URIScanner;
import com.example.component_scan.scanner.data_structure.UriAndMethod;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TestConfiguration implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new TestArgumentResolver(getUriAndMethods()));
    }

    private List<UriAndMethod> getUriAndMethods() {
        List<Class<?>> allClasses = new ClassesScanner(
            new CanonicalPathScanner("com.example")
        ).getAllClasses();
        return new URIScanner(new ControllerScanner(allClasses)).extractUriAndMethods();
    }
}
