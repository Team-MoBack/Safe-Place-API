package com.moBack.backend.service;

import com.moBack.backend.config.TokenConfig;
import com.moBack.backend.util.TokenUtil;

import java.util.Optional;

public interface TokenService {

    public String saveToken(String email);
    public boolean checkToken(String tokenFromClient);
    public void deleteToken(String email);
}
