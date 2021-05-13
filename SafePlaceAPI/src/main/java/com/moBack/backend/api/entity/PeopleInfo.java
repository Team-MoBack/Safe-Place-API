package com.moBack.backend.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PeopleInfo {
    private int id;
    private int numberOfCurrentPeople;
    private int numberOfNewPeople;
    private String time;
}

