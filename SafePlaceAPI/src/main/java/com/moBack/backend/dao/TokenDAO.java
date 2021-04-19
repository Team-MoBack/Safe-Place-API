package com.moBack.backend.dao;

public interface TokenDAO {

    public String saveToken(String email);
    public boolean checkToken(String token);
    public void deleteToken(String email);
}
