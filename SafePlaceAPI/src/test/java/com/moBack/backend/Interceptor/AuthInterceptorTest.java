package com.moBack.backend.Interceptor;

import com.moBack.backend.AbstractTest;
import com.moBack.backend.service.TokenService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import javax.servlet.http.*;

public class AuthInterceptorTest extends AbstractTest {

    @Autowired
    private AuthInterceptor authInterceptor;
    @MockBean
    private TokenService tokenService;
    @MockBean
    private HttpServletRequest httpServletRequest;
    @MockBean
    private HttpServletResponse httpServletResponse;

    @Test
    public void preHandleTest() throws Exception {
        String token = "115151515-test@gamil.com-slfksjflksdjfl";
        String bearer = "Bearer " + token;
        Mockito.when(httpServletRequest.getHeader(AuthInterceptor.AUTORIZATION_HEADER)).thenReturn(bearer);
        Mockito.when(tokenService.checkToken(token)).thenReturn(true);
        Assert.assertEquals(true,authInterceptor.preHandle(httpServletRequest,httpServletResponse,new Object()));
        Mockito.when(tokenService.checkToken(token)).thenReturn(false);
        Assert.assertEquals(false,authInterceptor.preHandle(httpServletRequest,httpServletResponse,new Object()));
    }
}
