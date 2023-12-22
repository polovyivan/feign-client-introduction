package com.polovyi.ivan.example_5;

import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import java.io.IOException;
import java.lang.reflect.Type;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomDecoder implements Decoder {

    private final JacksonDecoder jacksonDecoder;

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        if (type.getTypeName().equals("java.lang.String") ||
                type.getTypeName().equals("byte[]")
        ) {
            return new Default().decode(response, type);
        } else {
            return jacksonDecoder.decode(response, type);
        }
    }
}
