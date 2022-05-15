package com.holamountain.userdomain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class UserMyInfoRequest {
    private Long userId;
}
