package com.polovyi.ivan.controller;

import com.polovyi.ivan.dto.CreateCustomerRequest;
import com.polovyi.ivan.dto.CustomerResponse;
import com.polovyi.ivan.service.CustomerService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(path = "/test")
    @ResponseStatus(HttpStatus.OK)
    public String test() {
        return "Return from test endpoint";
    }

    @GetMapping(path = "/test-secured")
    @ResponseStatus(HttpStatus.OK)
    public String testSecured() {
        return "Return from test secured endpoint";
    }

    @GetMapping(path = "/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getAllCustomersWithFilters(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAt) {
        return customerService.getCustomersWithFilters(fullName, phoneNumber, createdAt);
    }

    @PostMapping(path = "/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest,
            UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        String customerId = customerService.createCustomer(createCustomerRequest).getId();
        response.addHeader("location", uriBuilder.path("/customers/{id}").buildAndExpand(customerId).toUriString());
    }

}
