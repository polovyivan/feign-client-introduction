package com.polovyi.ivan.example_6;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuthorizationInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization",
                "Basic " + Base64.getEncoder()
                        .encodeToString("admin:admin".getBytes(StandardCharsets.UTF_8)));
    }
}
