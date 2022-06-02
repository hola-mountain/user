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

    public void setDefaultMessage() {
        this.resultMessage = "<script>alert('#resultMessage'); location.href='http://holam-front-s3.s3-website.ap-northeast-2.amazonaws.com/'</script>";
    }

    public void setVerifySuccessMessage() {
        this.resultMessage = this.resultMessage.replaceAll("#resultMessage", "이메일 인증에 성공하였습니다.");
    }
    public void setVerifyFailDuplNickNameMessage() {
        this.resultMessage = this.resultMessage.replaceAll("#resultMessage", "해당 닉네임의 회원이 존재합니다.");
    }
    public void setVerifyFailNoInfoInRedisMessage() {
        this.resultMessage = this.resultMessage.replaceAll("#resultMessage", "해당 인증정보가 만료되었습니다.");
    }
}
