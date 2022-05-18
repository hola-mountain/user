package com.holamountain.userdomain.webclient;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

@Component
public class WebClientConfg {

    private WebClient mountainWebClinet;

    @Value("${dmoain.client.mountain.url}")
    private String mountainUrl;

    public WebClientConfg(WebClient.Builder builder) {
        this.mountainWebClinet = builder.baseUrl(mountainUrl).build();
    }

    public WebClient getMountainWebClinet() {
        return this.mountainWebClinet;
    }
}
