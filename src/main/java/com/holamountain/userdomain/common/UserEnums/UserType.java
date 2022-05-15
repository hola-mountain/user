package com.holamountain.userdomain.common.UserEnums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor
public enum  UserType {
    ADMIN("ADMIN", "운영자"),
    CUSTOMER("CUSTOMER", "일반고객");

    private String name;
    private String description;
}
