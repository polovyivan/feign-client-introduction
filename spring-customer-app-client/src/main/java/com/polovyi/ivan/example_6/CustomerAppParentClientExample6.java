package com.polovyi.ivan.example_6;

import feign.RequestLine;

public interface CustomerAppParentClientExample6 {
    @RequestLine("GET /test")
    String test();
    @RequestLine("GET /test-secured")
    String testSecured();
}
