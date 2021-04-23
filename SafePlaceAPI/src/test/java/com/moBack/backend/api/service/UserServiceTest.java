package com.moBack.backend.api.service;

import com.moBack.backend.api.AbstractTest;
import com.moBack.backend.api.dao.AccountRepository;
import com.moBack.backend.api.dto.AccountDTO;
import com.moBack.backend.api.dto.LoginResultDTO;
import com.moBack.backend.api.dto.UserPwDTO;
import com.moBack.backend.dto.*;
import com.moBack.backend.api.entity.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

public class UserServiceTest extends AbstractTest {

    @Autowired
    private UserService userService;
    @MockBean
    private AccountRepository accountRepository;
    private String email1 = "test1@gmail.com";
    private String email2 = "test2@gmail.com";
    private AccountDTO dto1 = AccountDTO.builder()
            .email(email1)
            .password("pw1")
            .build();
    private AccountDTO dto2 = AccountDTO.builder()
            .email(email2)
            .password("pw2")
            .build();

    @Before
    public void setup() {
        Mockito.when(accountRepository.findByEmail(email1)).thenReturn(
                Optional.of(Account.builder()
                        .email("email1")
                        .password("pw1")
                        .build()));
        Mockito.when(accountRepository.findByEmail(email2)).thenReturn(Optional.empty());
    }

    @Test
    public void registerUserTest() {
        Assert.assertEquals("fail",userService.registerUser(dto1));
        Assert.assertEquals("success",userService.registerUser(dto2));
    }

    @Test
    public void login() {
        UserPwDTO user1 = UserPwDTO.builder()
                .email(email1)
                .password("pw1")
                .build();
        UserPwDTO user2 = UserPwDTO.builder()
                .email(email2)
                .password("pw2")
                .build();
        Assert.assertEquals(LoginResultDTO.builder().isSuccessful(true).message("Login Success").build(),userService.login(user1));
        Assert.assertEquals(LoginResultDTO.builder().isSuccessful(false).message("Email or password is incorrect").build(),userService.login(user2));
    }
}
