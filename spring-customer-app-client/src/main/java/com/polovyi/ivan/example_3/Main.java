package com.polovyi.ivan.example_3;

import feign.Feign;
import feign.Logger.Level;
import feign.slf4j.Slf4jLogger;

public class Main {

    public static void main(String[] args) {

        CustomerAppClientExample3 clientWithLogs = Feign.builder()
                .requestInterceptor(new AuthorizationInterceptor())
                .logLevel(Level.FULL)
                .logger(new Slf4jLogger())
                .target(CustomerAppClientExample3.class,
                        "http://localhost:8001/spring-customer-app");

        String responseWithLogs = clientWithLogs.testSecured();
        System.out.println("responseWithLogs = " + responseWithLogs);
    }
}