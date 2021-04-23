package com.moBack.backend.api.Interceptor;

import com.moBack.backend.api.service.TokenService;
import com.moBack.backend.api.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    public static final String AUTORIZATION_HEADER = "Authorization";

    private TokenService tokenService;
    public AuthInterceptor(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public boolean isValid(String token) {
        return token != null && token.isEmpty() == false && tokenService.checkToken(token);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = TokenUtil.getToken(request.getHeader(AUTORIZATION_HEADER));
        if (isValid(token) == false) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return isValid(token);
    }
}
