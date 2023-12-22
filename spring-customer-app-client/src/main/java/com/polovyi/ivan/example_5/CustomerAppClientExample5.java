package com.polovyi.ivan.example_5;

import com.polovyi.ivan.dto.CreateCustomerRequest;
import com.polovyi.ivan.dto.CustomerResponse;
import feign.Headers;
import feign.RequestLine;
import java.util.List;

public interface CustomerAppClientExample5 {
    @RequestLine("GET /test")
    String test();
    @RequestLine("GET /test-secured")
    String testSecured();
    @RequestLine("GET /customers")
    List<CustomerResponse> getCustomers();
    @RequestLine("POST /customers")
    @Headers({"Content-Type:  application/json", "Accept: application/json"})
    void createCustomer(CreateCustomerRequest body);
}
