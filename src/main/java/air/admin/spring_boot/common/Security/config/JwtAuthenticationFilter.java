package air.admin.spring_boot.common.Security.config;

import air.admin.spring_boot.common.Security.service.CustomerUserDetailsService;
import air.admin.spring_boot.login.entity.MyUserDetails;
import air.admin.spring_boot.login.entity.User;
import air.admin.spring_boot.login.mapper.Loginmapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtTokenProvider jwtTokenProvider; // JWT 处理类

    private final RedisTemplate<String, Object> redisTemplate;
    @Resource
    private Loginmapper loginmapper;

    @Autowired
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, CustomerUserDetailsService customerUserDetailsService, RedisTemplate<String, Object> redisTemplate) {

        this.jwtTokenProvider = jwtTokenProvider;
        // 用户详情服务
        this.redisTemplate = redisTemplate;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = resolveToken(request); // 从请求中解析出 JWT
        if (token != null && jwtTokenProvider.validateToken(token)) {

            String username = jwtTokenProvider.getUsernameFromToken(token); // 从 token 中获取用户名
            User user = loginmapper.selectOne(new LambdaQueryWrapper<User>()
                    .eq(User::getUsername, username));
            // 如果用户名不为空且还没有认证
            if (username != null) {
                // 从redis加载用户详情
                MyUserDetails myUserDetails = (MyUserDetails)redisTemplate.opsForValue().get(user.getStatu() +":"+token);
                // 创建认证信息并设置到上下文中
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(myUserDetails, null,
                                myUserDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response); // 继续传递过滤链
    }

    @Nullable
    public String resolveToken(@NotNull HttpServletRequest request) {
        String bearerToken = request.getHeader("token");
        return bearerToken;
    }
}

