package air.admin.spring_boot.common.Security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;
    private final long EXPIRATION_TIME = 864_000_000; // 10 天

    // 生成 JWT Token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(String.valueOf(username))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    // 验证 JWT Token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true; // 返回 true 说明 token 有效
        } catch (Exception e) {
            return false; // 返回 false 说明 token 不有效
        }
    }

    // 从 JWT Token 中获取用户名
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}


