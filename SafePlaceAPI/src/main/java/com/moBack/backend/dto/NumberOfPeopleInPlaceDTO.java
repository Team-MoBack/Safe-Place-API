package com.moBack.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class NumberOfPeopleInPlaceDTO {

    private int id;
    private int numberOfPeople;

}
