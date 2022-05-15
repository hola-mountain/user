package com.holamountain.userdomain.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "유저 도메인",
                version = "1.0",
                description = "유저 도메인 API"
        )
)
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi memberApi() {

        return GroupedOpenApi.builder()
                .group("user")
                .pathsToMatch("/users/**")
                .build();
    }
}