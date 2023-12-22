package com.polovyi.ivan.example_5;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.polovyi.ivan.dto.CreateCustomerRequest;
import feign.Feign;
import feign.Logger.Level;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

public class Main {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .enable(SerializationFeature.INDENT_OUTPUT)
            .setSerializationInclusion(Include.NON_NULL);

    public static void main(String[] args) {
        CustomerAppClientExample5 client = Feign.builder()
                .requestInterceptor(new AuthorizationInterceptor())
                .logLevel(Level.FULL)
                .logger(new Slf4jLogger())
                .encoder(new JacksonEncoder(OBJECT_MAPPER))
                .decoder(new CustomDecoder(new JacksonDecoder(OBJECT_MAPPER)))
                .errorDecoder(new CustomErrorDecoder(OBJECT_MAPPER))
                .target(CustomerAppClientExample5.class,
                        "http://localhost:8001/spring-customer-app");
        client.createCustomer(new CreateCustomerRequest(null, "17737278341", "address"));
    }
}