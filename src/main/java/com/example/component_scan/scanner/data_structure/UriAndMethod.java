package com.example.component_scan.scanner.data_structure;

import org.springframework.http.HttpMethod;

public class UriAndMethod {
    private final String Uri;
    private final HttpMethod httpMethod;

    public UriAndMethod(String uri, HttpMethod httpMethod) {
        Uri = uri;
        this.httpMethod = httpMethod;
    }

    public String getUri() {
        return Uri;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }
}
