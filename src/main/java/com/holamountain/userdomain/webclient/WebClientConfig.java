package com.holamountain.userdomain.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfig {

    private WebClient mountainWebClinet;

//    @Value("${domain.client.mountain.url}")
    private String mountainUrl;

    public WebClientConfig(@Value("${domain.client.mountain.url}") String mounatinDomainUrl, WebClient.Builder builder) {

        this.mountainWebClinet = builder.baseUrl(mounatinDomainUrl).build();
    }

    public WebClient getMountainWebClinet() {
        return this.mountainWebClinet;
    }
}
