package com.polovyi.ivan.example_1;

import feign.RequestLine;

public interface CustomerAppClientExample1 {

    @RequestLine("GET /test")
    String test();

}
