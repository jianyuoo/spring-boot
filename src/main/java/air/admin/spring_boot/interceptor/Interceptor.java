//package air.admin.spring_boot.interceptor;
//
//
//import air.admin.spring_boot.common.RedisObjectService;
//import air.admin.spring_boot.common.Security.config.JwtAuthenticationFilter;
//import air.admin.spring_boot.common.Security.config.JwtTokenProvider;
//import air.admin.spring_boot.login.entity.User;
//import air.admin.spring_boot.login.mapper.Loginmapper;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import jakarta.annotation.Resource;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import java.util.concurrent.TimeUnit;
//
//@Component
//public class Interceptor implements HandlerInterceptor {
//
//    public final String secretKey = "iking@.com";
//
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = request.getHeader("token"); // 从请求头获取用户 ID，具体实现依据你的设计
//        if (token != null && redisTemplate.get("医生:"+ token)!=null||redisTemplate.get("患者:"+ token)!=null) {
//            // 用户会话存在，继续请求
//            if(redisTemplate.get ("医生:"+ token) !=null){
//                refreshUserSession(token,"医生");
//            }
//            if(redisTemplate.get ("患者:"+ token) !=null){
//                refreshUserSession(token,"患者");
//            }
//            return true;
//        } else {
//            // 用户会话不存在，拒绝请求
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//            return false;
//        }
//    }
//
//
//
//}
