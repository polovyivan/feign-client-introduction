package com.polovyi.ivan.example_4;

import com.polovyi.ivan.dto.CreateCustomerRequest;
import com.polovyi.ivan.dto.CustomerResponse;
import feign.Headers;
import feign.RequestLine;
import java.util.List;

public interface CustomerAppClientExample4 {

    @RequestLine("GET /customers")
    List<CustomerResponse> getCustomers();

    @RequestLine("POST /customers")
    @Headers({"Content-Type:  application/json", "Accept: application/json"})
    void createCustomer(CreateCustomerRequest body);
}