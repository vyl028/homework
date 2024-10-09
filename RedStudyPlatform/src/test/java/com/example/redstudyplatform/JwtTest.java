package com.example.redstudyplatform;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void testGenAndParse(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "张三");

        // 生成新的 JWT
        String token = JWT.create()
                .withClaim("user", claims) // 添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3)) // 3小时有效期
                .sign(Algorithm.HMAC256("xu")); // 指定算法，配置密钥
        System.out.println("Generated Token: " + token);

        // 解析生成的 JWT
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("xu")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token); // 验证并解析 token
        Map<String, Claim> parsedClaims = decodedJWT.getClaims();
        System.out.println("Parsed Claims: " + parsedClaims.get("user"));
    }
}
