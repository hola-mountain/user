package com.holamountain.userdomain.dto.request.users;

import com.holamountain.userdomain.common.Message.UsersExceptionMessage;
import com.holamountain.userdomain.exception.RequestEmptyException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class UserRegistrationRequest {
    private String nickName;
    private String password;

    @Schema(nullable = true, description = "이메일은 선택값입니다.")
    private String email;

    public void validCheck() {
        if (StringUtils.isBlank(nickName))
            throw new RequestEmptyException(UsersExceptionMessage.RequireUserNickNameException.getMessage());
        if (StringUtils.isBlank(password))
            throw new RequestEmptyException(UsersExceptionMessage.RequireUserPasswordException.getMessage());
    }
}
