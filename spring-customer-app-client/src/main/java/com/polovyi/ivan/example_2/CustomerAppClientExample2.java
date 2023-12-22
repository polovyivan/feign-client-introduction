package com.polovyi.ivan.example_2;

import feign.RequestLine;

public interface CustomerAppClientExample2 {
    @RequestLine("GET /test")
    String test();
    @RequestLine("GET /test-secured")
    String testSecured();
}
