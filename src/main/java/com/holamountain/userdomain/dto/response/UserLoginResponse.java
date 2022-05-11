package com.holamountain.userdomain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserLoginResponse {
    private String userId;
    private String userType;
}
