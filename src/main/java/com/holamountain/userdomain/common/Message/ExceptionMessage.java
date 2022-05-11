
package com.holamountain.userdomain.common.Message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Getter
public enum  ExceptionMessage {
    RequireUserNickNameException("BadRequestException", "이름을 입력해 주세요."),
    RequireUserPasswordException("BadRequestException", "비밀번호를 입력해 주세요.");
    HasNoUserException("BadRequestException", "");

    private String type;
    private String message;
}
