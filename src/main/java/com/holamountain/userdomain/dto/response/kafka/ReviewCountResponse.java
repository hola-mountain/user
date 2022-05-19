package com.holamountain.userdomain.dto.response.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ReviewCountResponse {
    Long userId;
    Long ratingId;
    Integer add;
}
