package com.holamountain.userdomain.dto.request;

import com.holamountain.userdomain.common.Message.UsersExceptionMessage;
import com.holamountain.userdomain.exception.RequestEmptyException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor @NoArgsConstructor
@Getter
@ToString
public class UserLoginRequest {
    private String nickName;
    private String password;
    private String userId;

    public void validCheck() {
        if (StringUtils.isBlank(nickName)) throw new RequestEmptyException(UsersExceptionMessage.RequireUserNickNameException.getMessage());
        if (StringUtils.isBlank(password)) throw new RequestEmptyException(UsersExceptionMessage.RequireUserPasswordException.getMessage());
    }
}
