package com.holamountain.userdomain.dto.request.users;

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
public class UserLogoutRequest {
    private String userId;

    public void validCheck() {
        if (StringUtils.isBlank(userId)) throw new RequestEmptyException(UsersExceptionMessage.EmptyRequestMessage.getMessage());
    }
}
