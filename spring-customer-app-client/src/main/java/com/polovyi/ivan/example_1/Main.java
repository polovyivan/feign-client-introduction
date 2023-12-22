package com.polovyi.ivan.example_1;

import feign.Feign;

public class Main {

    public static void main(String[] args) {

        CustomerAppClientExample1 client = Feign.builder()
                .target(CustomerAppClientExample1.class,
                        "http://localhost:8001/spring-customer-app");

        String test = client.test();
        System.out.println("test = " + test);
    }

}