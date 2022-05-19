package com.holamountain.userdomain.dto.response.mypage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MyMountainReviewResponse {
    private Long ratingId;
    private Integer star;
    private String comment;
    private LocalDate regdate;
    private String title;
    private String thumbImg;
    private String nickname;
    private Long recommendNum;

    private Long mountainId;
    private String name;
}
