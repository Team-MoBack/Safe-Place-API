package com.moBack.backend.dto;

import lombok.*;

@Builder
@Data
public class LoginResultDTO {
    private boolean isSuccessful;
    private String message;
    private String token;
}
