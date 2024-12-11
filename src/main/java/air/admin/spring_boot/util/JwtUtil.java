package air.admin.spring_boot.util;

import air.admin.spring_boot.base.constant.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.sql.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtParser;

public class JwtUtil {

    private static final long tokenExpiration = 60 * 60 * 1000L;
    private static final SecretKey tokenSignKey = Keys.hmacShaKeyFor("M0PKKI6pYGVWWfDZw90a0lTpGYX1d4AQ".getBytes());

    public static String createToken(Long userId, String username) {
        return Jwts.builder().
                subject("USER_INFO").
                expiration(new Date(System.currentTimeMillis() + tokenExpiration)).
                claim("userId", userId).
                claim("username", username).
                signWith(tokenSignKey).
                compact();
    }

    public static Claims parseToken(String token) throws LeaseException {

        if (token==null){
            throw new LeaseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }

        try{
            JwtParser jwtParser = Jwts.parser()
                    .setSigningKey(tokenSignKey) // 设置用于验证签名的密钥
                    .build();
            Claims claims = jwtParser.parseClaimsJws(token).getBody(); // 解析并获取主体
            return jwtParser.parseClaimsJws(token).getBody(); // 返回解析成功后的 Claims
        }catch (ExpiredJwtException e){
            throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
        }catch (JwtException e){
            throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
        }
    }
}
