package com.polovyi.ivan.example_7;

import feign.RequestLine;

public interface CustomerAppParentClientExample7 {

    @RequestLine("GET /test")
    String test();

    @RequestLine("GET /test-secured")
    String testSecured();

}
