package com.moBack.backend.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PeopleInfoDTO {
    private int id;
    private int numberOfCurrentPeople;
    private int numberOfNewPeople;
}
