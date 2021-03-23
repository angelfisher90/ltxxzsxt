package com.ywy.ltxxzsxt.utils;

/**
 * @description: TODO
 * @author: ywy
 * @date: 2021/1/19 21:50
 * @version: 1.0.0
 */

/**
 * jwt工具类
 */
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class JwtUtils {

    private static final String SING = "!@#$%^&*(^432142@#$^)&";

    public static String getToken(Map<String, String> map) {
        Calendar calendar = Calendar.getInstance();
        //设置过期时间为2天
        calendar.add(Calendar.YEAR, 10);
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        String token = builder.withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(SING));
        return token;
    }

    public static String getToken() {
        Map<String, String> map = new HashMap<String, String>(4);
        return getToken(map);
    }

    public static boolean verify(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SING)).build();
        try {
            jwtVerifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            //throw new RuntimeException("401");
            return false;
        }
    }


    public static DecodedJWT decode(String token){
        try {
            return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}