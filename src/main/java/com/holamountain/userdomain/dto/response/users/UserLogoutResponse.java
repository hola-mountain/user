package com.holamountain.userdomain.dto.response.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class UserLogoutResponse {
    private String resultMessage;

    public void setLogoutSuccessMessage() {
        this.resultMessage = "로그아웃에 성공하였습니다.";
    }
}
