package com.holamountain.userdomain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MyBadgeInfoResponse {
    private Long achievementId;
    private Long achievementNum;
    private Long badgeId;
    private Long userId;
}
