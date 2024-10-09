package com.example.redstudyplatform.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final String KEY = "YChen";

    // 接收业务数据，生成token并返回
    public static String genToken(Map<String, Object> claims) {
        JWTCreator.Builder builder = JWT.create();

        // 将传入的业务数据作为JWT的声明（claim）添加到JWT中
        claims.forEach((key, value) -> {
            if (value instanceof Integer) {
                builder.withClaim(key, (Integer) value);
            } else if (value instanceof Long) {
                builder.withClaim(key, (Long) value);
            } else if (value instanceof Boolean) {
                builder.withClaim(key, (Boolean) value);
            } else if (value instanceof Double) {
                builder.withClaim(key, (Double) value);
            } else if (value instanceof String) {
                builder.withClaim(key, (String) value);
            } else {
                throw new IllegalArgumentException("Unsupported claim type for key: " + key);
            }
        });

        return builder
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) // 设置JWT的过期时间，12小时后过期
                .sign(Algorithm.HMAC256(KEY)); // 使用HMAC256算法签名
    }

    // 接收token，验证token，并返回业务数据
    public static Map<String, Object> parseToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token);

        Map<String, Object> claims = new HashMap<>();
        decodedJWT.getClaims().forEach((key, claim) -> {
            Object value;
            if (claim.asInt() != null) {
                value = claim.asInt();
            } else if (claim.asLong() != null) {
                value = claim.asLong();
            } else if (claim.asBoolean() != null) {
                value = claim.asBoolean();
            } else if (claim.asDouble() != null) {
                value = claim.asDouble();
            } else {
                value = claim.asString();
            }
            claims.put(key, value);
        });

        return claims;
    }
}