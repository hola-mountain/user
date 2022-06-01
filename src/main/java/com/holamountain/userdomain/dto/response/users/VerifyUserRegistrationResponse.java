package com.holamountain.userdomain.dto.response.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerifyUserRegistrationResponse {
    private String resultMessage;

    public void setVerifySuccessMessage() {
        this.resultMessage = "이메일 인증에 성공하였습니다.";
    }
}
