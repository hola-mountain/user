package com.holamountain.userdomain.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class JwtTokenInfo {
    private Long userId;
    private String refreshToken;
    private Date refreshTokenExpirationTime;
}
