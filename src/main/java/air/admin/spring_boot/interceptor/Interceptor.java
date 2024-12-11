package air.admin.spring_boot.interceptor;

import air.admin.spring_boot.util.common.LoginUserHolder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class Interceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、OPTIONS 请求放行
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        //2、、获取请求头中的token
        String token = request.getHeader("Authorization");
        if (token == null) {
            response.setStatus(401);
            return false;
        }
        //3、验证token是否合法
        String ss= JWT.decode(token).getAudience().toString();
        try {
            //验证token是否合法
            ss= JWT.decode(token).getAudience().toString();
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
        //4、如果token合法，将用户信息存储到request域中
        request.setAttribute("userInfo", "用户信息");

        //5、jwt验证token是否过期
        JWTVerifier jwtveryfy = JWT.require(Algorithm.HMAC256("1234567890".getBytes())).build();
        jwtveryfy.verify(token);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginUserHolder.clear();
    }
}
