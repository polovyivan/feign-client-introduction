package com.polovyi.ivan.example_6;

import com.polovyi.ivan.dto.CreateCustomerRequest;
import com.polovyi.ivan.dto.CustomerResponse;
import feign.Headers;
import feign.RequestLine;
import java.util.List;

public interface CustomerAppClientExample6 extends CustomerAppParentClientExample6 {

    @RequestLine("GET /customers")
    List<CustomerResponse> getCustomers();

    @RequestLine("POST /customers")
    @Headers({"Content-Type:  application/json", "Accept: application/json"})
    void createCustomer(CreateCustomerRequest body);

    default void createCustomerWithValidation(CreateCustomerRequest body) {
        if (body.getFullName() == null || body.getPhoneNumber() == null || body.getAddress() == null) {
            throw new IllegalArgumentException("Invalid request body");
        }
        createCustomer(body);
    }
}
