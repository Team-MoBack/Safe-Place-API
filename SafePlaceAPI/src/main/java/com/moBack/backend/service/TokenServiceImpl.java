package com.moBack.backend.service;

import com.moBack.backend.dao.TokenDAO;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    private TokenDAO tokenDAO;

    public TokenServiceImpl(TokenDAO tokenDAO) {
        this.tokenDAO = tokenDAO;
    }

    @Override
    public String saveToken(String email) {
        return tokenDAO.saveToken(email);
    }

    @Override
    public boolean checkToken(String tokenFromClient) {
        return tokenDAO.checkToken(tokenFromClient);
    }

    @Override
    public void deleteToken(String email) {
        tokenDAO.deleteToken(email);
    }
}
