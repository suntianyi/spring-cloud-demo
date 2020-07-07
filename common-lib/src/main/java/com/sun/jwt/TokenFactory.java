package com.sun.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Component
@Slf4j
public class TokenFactory {
    /**
     * token的过期时间，单位秒（86400为1天）
     */
    private Integer expirationTime = 86400;

    /**
     * 使用的签名KEY
     */
    private String signingKey = "TOKEN!&^#9$23loud";

    /**
     * 授权信息header前缀
     */
    private String headerPrefix = "Bearer ";

    /**
     * 生成token
     */
    public String generateToken(Map<String, Object> userMap) {
        return codeInfo(userMap);
    }

    /**
     * 验证token
     */
    public JwtResult validateToken(String token) {
        try {
            Claims claims = parseJWT(token);
            return JwtResult.builder()
                    .result(JwtResult.ResultEnum.FRESH)
                    .info(parseToken(claims))
                    .build();
        } catch (JwtException e) {
            log.error("Expire Token.", e);
            return JwtResult.builder()
                    .result(JwtResult.ResultEnum.EXPIRE)
                    .build();
        } catch (Exception e) {
            log.error("Invalid Token.", e);
            return JwtResult.builder()
                    .result(JwtResult.ResultEnum.EXPIRE)
                    .build();
        }
    }

    /**
     * 解析JWT字符串
     */
    public Claims parseJWT(String token) {
        return Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token.replace(headerPrefix, ""))
                .getBody();
    }

    /**
     * 编码信息
     */
    public String codeInfo(Map<String, Object> info) {
        return headerPrefix + Jwts.builder()
                .setClaims(info)
                .setExpiration(new DateTime().plusSeconds(expirationTime).toDate())
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
    }

    /**
     * 解析Claims
     */
    public Map<String, Object> parseToken(Claims claims) {
        Set<String> keys = claims.keySet();
        Map<String, Object> info = new HashMap<>(keys.size());
        for (String key : keys) {
            info.put(key, claims.get(key));
        }
        return info;
    }

}
