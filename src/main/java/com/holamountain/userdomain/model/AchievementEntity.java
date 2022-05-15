package com.holamountain.userdomain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Table(value = "ACHIEVMENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AchievementEntity {
    @Id
    @Column(value = "achievementId")
    private Long achievementId;

    @Column(value = "achievementNum")
    private Long achievementNum;

    @Column(value = "badgeId")
    private Long badgeId;

    @Column(value = "userId")
    private Long userId;

    @Transient
    List<BadgeEntity> badgeEntityList = new ArrayList<>();
}
