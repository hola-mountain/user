
package com.holamountain.userdomain.router;

import com.holamountain.userdomain.dto.request.users.UserLoginRequest;
import com.holamountain.userdomain.dto.request.users.UserRegistrationRequest;
import com.holamountain.userdomain.dto.response.exception.ExceptionReponse;
import com.holamountain.userdomain.dto.response.users.UserLoginResponse;
import com.holamountain.userdomain.dto.response.users.UserRegistrationResponse;
import com.holamountain.userdomain.handler.UserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@EnableWebFlux
public class UserRouter {

    @RouterOperations({
        @RouterOperation(
            path = "/users/login",
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE },
            beanClass = UserHandler.class,
            method = RequestMethod.POST,
            beanMethod = "login",
            operation = @Operation(
                description = "유저 로그인 API",
                operationId = "login",
                requestBody = @RequestBody(
                    content = @Content(
                        schema = @Schema(
                                implementation = UserLoginRequest.class,
                                required = true
                        )
                    )
                ),
                responses = {
                    @ApiResponse(
                        responseCode = "200",
                        content = @Content(
                            schema = @Schema(
                                    implementation = UserLoginResponse.class,
                                    required = true
                            )
                        )
                    ),
                    @ApiResponse(
                        responseCode = "400",
                        content = @Content(
                            schema = @Schema(
                                implementation = ExceptionReponse.class,
                                required = true
                            )
                        )
                    )
                }
            )
        ),
        @RouterOperation(
            path = "/users/join",
            consumes = { MediaType.APPLICATION_JSON_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE },
            beanClass = UserHandler.class,
            method = RequestMethod.POST,
            beanMethod = "join",
            operation = @Operation(
                description = "유저 회원가입 API",
                operationId = "join",
                requestBody = @RequestBody(
                    content = @Content(
                        schema = @Schema(
                                implementation = UserRegistrationRequest.class,
                                required = true
                        )
                    )
                ),
                responses = {
                    @ApiResponse(
                        responseCode = "200",
                        content = @Content(
                            schema = @Schema(
                                    implementation = UserRegistrationResponse.class,
                                    required = true
                            )
                        )
                    ),
                    @ApiResponse(
                        responseCode = "400",
                        content = @Content(
                            schema = @Schema(
                                    implementation = ExceptionReponse.class,
                                    required = true
                            )
                        )
                    )
                }
            )
        )
    })
    @Bean
    public RouterFunction<ServerResponse> usRouter(UserHandler userHandler) {
        return RouterFunctions.route()
            .path("/users", routerBuilder ->
                routerBuilder.nest(accept(MediaType.APPLICATION_JSON), builder ->
                    builder
                        .POST("/join", userHandler::join)
                        .POST("/login", userHandler::login)
//                        .POST("me", userHandler:myInfo)
                )
            )
            .build();
    }
}
