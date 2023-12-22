package com.polovyi.ivan.example_7;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String responseAsString = new String(response.body()
                    .asInputStream().readAllBytes(), "UTF-8");
            ClientExceptionDetails clientExceptionDetails = objectMapper
                    .readValue(responseAsString, ClientExceptionDetails.class);
            clientExceptionDetails.setMethod(methodKey);
            throw new ClientException(clientExceptionDetails);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while decoding error response.", e);
        }
    }
}
