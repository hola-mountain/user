package com.holamountain.userdomain.dto.response.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserLoginResponse {
    private Long userId;

    private String accessToken;
    private String refreshToken;
}
