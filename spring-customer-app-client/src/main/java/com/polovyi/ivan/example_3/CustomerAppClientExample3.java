package com.polovyi.ivan.example_3;

import feign.RequestLine;

public interface CustomerAppClientExample3 {
    @RequestLine("GET /test")
    String test();
    @RequestLine("GET /test-secured")
    String testSecured();
}
