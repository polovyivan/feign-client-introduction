package com.polovyi.ivan.example_5;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.polovyi.ivan.dto.CreateCustomerRequest;
import com.polovyi.ivan.dto.CustomerResponse;
import feign.Feign;
import feign.Logger.Level;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import java.util.List;

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
                .decoder(new CustomDecoder(new JacksonDecoder(OBJECT_MAPPER)))
                .encoder(new JacksonEncoder(OBJECT_MAPPER))
                .target(CustomerAppClientExample5.class,
                        "http://localhost:8001/spring-customer-app");

        List<CustomerResponse> customers = client.getCustomers();
        System.out.println("customers = " + customers);

        client.createCustomer(new CreateCustomerRequest("fullName", "17737278341", "address"));
    }
}