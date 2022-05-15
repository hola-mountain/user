
package com.holamountain.userdomain.model;

import com.holamountain.userdomain.common.UserEnums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table(value = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor @Builder
public class UserEntity {
    @Id
    @Column(value = "userId")
    private Long userId;

    @Column(value = "userType")
    private UserType userType;

    @Column(value = "nickName")
    private String nickName;

    @Column(value = "email")
    private String email;

    @Column(value = "password")
    private String password;

    @Column(value = "regDate")
    private Timestamp regDate;

    @Column(value = "statusYn")
    private boolean statusYn;
}
