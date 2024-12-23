package air.admin.spring_boot.module.registered.service;

import air.admin.spring_boot.common.Security.config.JwtTokenProvider;
import air.admin.spring_boot.util.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


public class registerService {

    @Value("${jwt.secret}")
    private String secretKey;

    public String getUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            // 处理 Token 过期逻
            return null;
        } catch (SignatureException e) {
            // 处理 Token 签名无效的逻辑
            return null;
        } catch (Exception e) {
            // 其他异常处理
            return null;
        }
    }


}

