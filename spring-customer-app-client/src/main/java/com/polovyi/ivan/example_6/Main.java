package com.polovyi.ivan.example_6;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.polovyi.ivan.dto.CreateCustomerRequest;
import feign.Feign;
import feign.Logger.Level;
import feign.jackson.JacksonDecoder;
import feign.slf4j.Slf4jLogger;

public class Main {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .enable(SerializationFeature.INDENT_OUTPUT)
            .setSerializationInclusion(Include.NON_NULL);

    public static void main(String[] args) {
        CustomerAppClientExample6 clientWithLogs = Feign.builder()
                .requestInterceptor(new AuthorizationInterceptor())
                .logLevel(Level.FULL)
                .logger(new Slf4jLogger())
                .decoder(new JacksonDecoder(OBJECT_MAPPER))
                .decoder(new CustomDecoder(new JacksonDecoder(OBJECT_MAPPER)))
                .errorDecoder(new CustomErrorDecoder(OBJECT_MAPPER))
                .target(CustomerAppClientExample6.class,
                        "http://localhost:8001/spring-customer-app");
        clientWithLogs.createCustomer(new CreateCustomerRequest(null, "17737278341", "address"));
    }
}