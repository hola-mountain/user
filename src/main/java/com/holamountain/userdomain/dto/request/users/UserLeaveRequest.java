package com.holamountain.userdomain.dto.request.users;

import com.holamountain.userdomain.common.Message.MypageExceptionMessage;
import com.holamountain.userdomain.exception.RequestEmptyException;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor @NoArgsConstructor
@Getter
@ToString
public class UserLeaveRequest {
    private String userId;

    public void validCheck() {
        if (StringUtils.isBlank(userId))
            throw new RequestEmptyException(MypageExceptionMessage.UnAuthorizedException.getMessage());
    }
}
