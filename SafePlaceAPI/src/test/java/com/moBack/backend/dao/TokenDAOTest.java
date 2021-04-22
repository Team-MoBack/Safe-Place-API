package com.moBack.backend.dao;

import com.moBack.backend.AbstractTest;
import com.moBack.backend.util.TokenUtil;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TokenDAOTest extends AbstractTest {

    @MockBean
    private StringRedisTemplate redisTemplate;
    @MockBean
    private BoundValueOperations boundValueOperations;
    private String tokenFromClient1 = "161616616-test1@gmail.com-lfksjlfkdjfsl";
    private String tokenFromClient2 = "161616616-test2@gmail.com-lfksjlfkdjfsl";

    @Autowired
    TokenDAO tokenDAO;

    @Test
    public void checkTokenTest() {
        Assert.assertFalse(tokenDAO.checkToken(""));
        Assert.assertFalse(tokenDAO.checkToken("1616161616"));
        Mockito.when(redisTemplate.boundValueOps("test1@gmail.com")).thenReturn(boundValueOperations);
        Mockito.when(redisTemplate.boundValueOps("test1@gmail.com").get()).thenReturn(tokenFromClient1);
        Assert.assertTrue(tokenDAO.checkToken(tokenFromClient1));
        Mockito.when(redisTemplate.boundValueOps("test1@gmail.com").get()).thenReturn(tokenFromClient2);
        Assert.assertFalse(tokenDAO.checkToken(tokenFromClient1));
    }
}
