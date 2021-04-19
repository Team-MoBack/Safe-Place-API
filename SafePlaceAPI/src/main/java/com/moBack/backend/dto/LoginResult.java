package com.moBack.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResult {

    private boolean isSuccessful;
    private String message;
    private String token;

}
