package com.moBack.backend.api.dto;

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