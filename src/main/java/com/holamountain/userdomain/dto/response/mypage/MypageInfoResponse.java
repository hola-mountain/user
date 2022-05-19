package com.holamountain.userdomain.dto.response.mypage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class MypageInfoResponse {
    private String email;
    private String nickName;
}
