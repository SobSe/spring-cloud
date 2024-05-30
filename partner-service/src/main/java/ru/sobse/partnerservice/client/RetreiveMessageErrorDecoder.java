package ru.sobse.partnerservice.client;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import ru.sobse.partnerservice.exception.ContractsNotFoundException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RetreiveMessageErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        byte[] body = {};
        try {
            if (response.body() != null) {
                body = Util.toByteArray(response.body().asInputStream());
            }
        } catch (IOException ignored) {
        }
        String message = new String(body, StandardCharsets.UTF_8);

        switch (response.status()) {
            case 400:
                return new IllegalArgumentException(message);
            case 404:
                if (methodKey.contains("getContracts")) {
                    return new ContractsNotFoundException(message);
                }
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}
