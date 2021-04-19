package com.moBack.backend.provider;

import com.moBack.backend.service.TokenService;
import com.moBack.backend.util.ApiConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.session.data.redis.RedisSessionRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final String AUTORIZATION_HEADER = "Authorization";

    private TokenService tokenService;
    public AuthInterceptor(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(AUTORIZATION_HEADER);
        if (token.contains("Bearer")) {
            return token.split(" ")[1];
        }
        return "";
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("pre handle!");
        String token = getToken(request);
        boolean result = token != null && token.isEmpty() == false && tokenService.checkToken(token);
        if (result == false) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return result;
    }
}
