
package com.holamountain.userdomain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userType;
    private String nickName;
    private String email;
    private String password;
    private Timestamp regDate;
    private boolean statusYn;
}
