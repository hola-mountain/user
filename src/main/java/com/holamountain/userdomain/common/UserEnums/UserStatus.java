package com.holamountain.userdomain.common.UserEnums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public enum  UserStatus {
    EXIST("EXIST", true),
    LEAVE("LEAVE", false);

    private String name;
    private boolean code;
}
