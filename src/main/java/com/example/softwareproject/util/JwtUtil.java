package com.example.softwareproject.util;

import com.example.softwareproject.pojo.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtil {
    private static String signKey = "weibo000000000000000000000000000000000000000000";
    private static Long expire = 43200000L;//12h

    /**
     * 生成JWT令牌
     *
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return
     */
    public static String generateJwt(Map<String, Object> claims) {
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     *
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 检查JWT令牌是否过期
     *
     * @param jwt JWT令牌
     * @return 是否过期
     */
    public static boolean isTokenExpired(String jwt) {
        try {
            Claims claims = parseJWT(jwt);
            return claims.getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }
    public static boolean setResponse(String text,HttpServletResponse response,boolean ans) throws IOException {
        log.info(text);
        Result error = Result.error(text);
        String json = JsonUtil.toJson(error);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(json);
        return ans;
    }
}
