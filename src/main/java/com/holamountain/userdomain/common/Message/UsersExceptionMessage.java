
package com.holamountain.userdomain.common.Message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Getter
public enum UsersExceptionMessage {
    UnAuthorizedException("UnAuthorizedException", "재로그인해 주세요."),
    RequireArgumentException("RequireArgumentException", "필수인자값을 확인해주세요."),
    RequireUserNickNameException("BadRequestException", "닉네임을 입력해 주세요."),
    RequireUserPasswordException("BadRequestException", "비밀번호를 입력해 주세요."),
    HasNoUserException("BadRequestException", "회원정보를 확인해주세요."),
    EmptyRequestMessage("BadRequestException", "입력값이 없습니다."),
    UserRegistrationDuplicationException("BadRequestException", "이미 등록된 회원입니다."),
    FailUserRegistrationMessage("BadRequestException", "등록이 실패했습니다. 다시 시도해 주세요.");

    private String type;
    private String message;
}
