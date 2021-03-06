package com.holamountain.userdomain.dto.request.jwt;

import com.holamountain.userdomain.common.Message.MypageExceptionMessage;
import com.holamountain.userdomain.common.Message.UsersExceptionMessage;
import com.holamountain.userdomain.exception.RequestEmptyException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class JwtTokenRequest {
    private String accessToken;
    private String refreshToken;

    public void validCheck() {
        if ( StringUtils.isBlank(accessToken) && StringUtils.isBlank(refreshToken))
            throw new RequestEmptyException(UsersExceptionMessage.RequireArgumentException.getMessage());
    }
}
