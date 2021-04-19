package com.moBack.backend.dao;

import com.moBack.backend.config.TokenConfig;
import com.moBack.backend.util.TokenUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TokenDAOImpl implements TokenDAO {

    private StringRedisTemplate redisTemplate;

    public TokenDAOImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String saveToken(String email) {
        String token = TokenUtil.createToken(email);
        redisTemplate.boundValueOps(email).set(token);
        return token;
    }

    @Override
    public boolean checkToken(String tokenFromClient) {
        if (tokenFromClient.isEmpty()) {
            return false;
        }
        Optional<String> email = TokenUtil.getEmailFromToken(tokenFromClient);
        if (email.isPresent() == false) {
            return false;
        }
        String tokenInRedis = redisTemplate.boundValueOps(email.get()).get();
        if (tokenFromClient.equals(tokenInRedis)) {
            redisTemplate.boundValueOps(email.get()).expire(TokenConfig.RETENTION);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteToken(String email) {
        redisTemplate.delete(email);
    }
}
