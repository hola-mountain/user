package com.holamountain.userdomain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "BADGES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadgeEntity {
    @Id
    @Column(value = "badgeId")
    private Long badgeId;

    @Column(value = "badgeType")
    private String badgeType;

    @Column(value = "goalNum")
    private Long goalNum;
}