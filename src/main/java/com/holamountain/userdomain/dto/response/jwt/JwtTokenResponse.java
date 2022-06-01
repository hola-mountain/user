package com.holamountain.userdomain.dto.response.jwt;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class JwtTokenResponse {
    private String accessToken;
    private String refreshToken;
}
