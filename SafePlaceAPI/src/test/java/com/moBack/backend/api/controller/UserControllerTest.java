package com.moBack.backend.api.controller;

import com.moBack.backend.api.AbstractTest;
import com.moBack.backend.api.dto.LoginResultDTO;
import com.moBack.backend.api.dto.UserPwDTO;
import com.moBack.backend.api.entity.Account;
import com.moBack.backend.api.rest.UserController;
import com.moBack.backend.api.service.TokenService;
import com.moBack.backend.api.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class UserControllerTest extends AbstractTest {

    @Autowired
    private UserController controller;
    @MockBean
    private UserService userService;
    @MockBean
    private TokenService tokenService;
    @MockBean
    private HttpServletResponse response;

    private UserPwDTO successUserPwDTO = new UserPwDTO();
    private UserPwDTO failureUserPwDTO = new UserPwDTO();
    private LoginResultDTO successLoginResultDTO = LoginResultDTO.builder().isSuccessful(true).build();
    private LoginResultDTO failureLoginResultDTO = LoginResultDTO.builder().isSuccessful(false).build();
    private String token = "16166161616-test@gmail.com-slfkjaslfdksjf";

    @Test
    public void successfulLoginTest() {
        Mockito.when(userService.login(successUserPwDTO)).thenReturn(successLoginResultDTO);
        Mockito.when(tokenService.saveToken(successUserPwDTO.getEmail())).thenReturn(token);
        controller.login(successUserPwDTO,response);
    }

    @Test(expected = ResponseStatusException.class)
    public void failedLoginTest() {
        Mockito.when(userService.login(failureUserPwDTO)).thenReturn(failureLoginResultDTO);
        controller.login(failureUserPwDTO,response);
    }

    @Test
    public void logoutTest() {
        Assert.assertEquals("success",controller.logout(token,response));
        Assert.assertEquals("fail",controller.logout("",response));
    }

    @Test
    public void findByValidIdTest() {
        int validId = 1;
        Mockito.when(userService.findById(validId)).thenReturn(Optional.of(new Account()));
        Assert.assertNotNull(controller.findById(validId));
    }

    @Test(expected = ResponseStatusException.class)
    public void findByInvalidIdTest() {
        int inValidId = 2;
        Mockito.when(userService.findById(inValidId)).thenReturn(Optional.empty());
        controller.findById(inValidId);
    }
}
