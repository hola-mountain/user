package com.holamountain.userdomain.dto.request.users;


import com.holamountain.userdomain.common.Message.UsersExceptionMessage;
import com.holamountain.userdomain.exception.RequestEmptyException;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString @Builder
public class VerifyUserRegistrationRequest {
    private String nickName;
    private String key;

    public void validCheck() {
        if (StringUtils.isBlank(nickName))
            throw new RequestEmptyException(UsersExceptionMessage.RequireUserNickNameException.getMessage());
        if (StringUtils.isBlank(key))
            throw new RequestEmptyException(UsersExceptionMessage.RequireUserPasswordException.getMessage());
    }
}
