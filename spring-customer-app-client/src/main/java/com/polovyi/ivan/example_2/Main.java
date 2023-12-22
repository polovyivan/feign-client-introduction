package com.polovyi.ivan.example_2;

import feign.Feign;

public class Main {

    public static void main(String[] args) {

        CustomerAppClientExample2 client = Feign.builder()
                .requestInterceptor(new AuthorizationInterceptor())
                .target(CustomerAppClientExample2.class,
                        "http://localhost:8001/spring-customer-app");

        String testSecured = client.testSecured();
        System.out.println("testSecured = " + testSecured);
    }

}