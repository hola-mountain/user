package com.holamountain.userdomain.common.Message;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum MypageExceptionMessage {
    UnAuthorizedException("UnAuthorizedException", "재로그인해 주세요."),
    NoDataFounedException("NoDataFounedException", "내 정보를 보려면 회원 가입을 진행 해주시기 바랍니다.");

    private String type;
    private String message;
}
