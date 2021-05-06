package com.moBack.backend.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReviewDTO {
    private int id;
    private String comment;
    private int rating;
    private int placeId;
    private int accountId;
}
