package com.moBack.backend.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}