package com.ywy.ltxxzsxt.interceptors;

/**
 * @description: TODO
 * @author: ywy
 * @date: 2021/01/19
 * @version: 1.0.0
 */

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ywy.ltxxzsxt.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tokenAdmin = request.getHeader("Authorization");
        System.out.println("Admin-Token:" + tokenAdmin);
        if(tokenAdmin!=null) {
            try {
                JwtUtils.verify(tokenAdmin);
            } catch (JWTVerificationException e) {
                throw new RuntimeException("401");
            }
        }
        return true;
    }
}
