package com.moBack.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}