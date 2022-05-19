package com.holamountain.userdomain.dto.response.mypage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MyFavoriteMountainResponse {
    private Long mountainId;
    private String name;
    private String shortDescription;

    private ArrayList<String> image = new ArrayList<>();

    private Long ratingId;
    private LocalDateTime regdate;
}
