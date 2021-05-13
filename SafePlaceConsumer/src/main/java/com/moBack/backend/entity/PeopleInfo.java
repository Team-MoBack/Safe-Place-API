package com.moBack.backend.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PeopleInfo {
    private int id;
    private int numberOfCurrentPeople;
    private int numberOfNewPeople;
    private String time;
}

