package com.moBack.backend.api.service;

public interface TokenService {

    public String saveToken(String email);
    public boolean checkToken(String tokenFromClient);
    public void deleteToken(String email);
}
