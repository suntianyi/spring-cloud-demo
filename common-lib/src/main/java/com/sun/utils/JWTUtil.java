package com.sun.utils;

import com.alibaba.fastjson.JSON;
import com.sun.constants.MessageConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JWTUtil {
    /**
     * 过期时间，单位秒（86400为1天）
     */
    private final static Integer expirationTime = 86400;

    /**
     * 签名KEY
     */
    private final static String signingKey = "TOKEN!&^#9$23loud";

    /**
     * 生成token
     */
    public static String generateToken(Map<String, Object> userMap) {
        return Jwts.builder()
                .setClaims(userMap)
                .setExpiration(new DateTime().plusSeconds(expirationTime).toDate())
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
    }

    /**
     * 验证token
     */
    public static String validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(token)
                    .getBody();
            Set<String> keys = claims.keySet();
            Map<String, Object> info = new HashMap<>(keys.size());
            for (String key : keys) {
                info.put(key, claims.get(key));
            }
            return JSON.toJSONString(info);
        } catch (JwtException e) {
            throw new JwtException(MessageConstants.TOKEN_EXPIRE);
        } catch (Exception e) {
            throw new RuntimeException(MessageConstants.TOKEN_INVALID);
        }
    }
}
