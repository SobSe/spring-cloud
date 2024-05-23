package ru.sobse.users.config;

import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import ru.sobse.users.client.ContractServiceClient;
import ru.sobse.users.client.ContractServiceClientImpl;
import ru.sobse.users.client.RetreiveMessageErrorDecoder;

@Configuration
public class ClientBeans {

    @Bean
    public ContractServiceClient contractServiceClient() {
        return new ContractServiceClientImpl(RestClient.builder()
                .baseUrl("http://localhost:8080/contract-ws")
                .build());
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new RetreiveMessageErrorDecoder();
    }
}
