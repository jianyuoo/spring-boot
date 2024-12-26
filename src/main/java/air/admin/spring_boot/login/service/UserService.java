package air.admin.spring_boot.login.service;

import air.admin.spring_boot.common.Security.config.JwtTokenProvider;
import air.admin.spring_boot.common.Security.service.CustomerUserDetailsService;
import air.admin.spring_boot.login.dto.LoginRequest;
import air.admin.spring_boot.login.entity.MyUserDetails;
import air.admin.spring_boot.login.entity.User;
import air.admin.spring_boot.login.mapper.loginmapper;
import air.admin.spring_boot.util.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserService extends ServiceImpl<loginmapper, User> {

    @Autowired
    private loginmapper loginmapper;

    /**
     * 获取认证入口
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    CustomerUserDetailsService customerUserDetailsService;


    public boolean existsByUsername(String username) {
        User user = loginmapper.findUsernameByUsername(username); // 查询用户名
        return user != null; // 如果 user 不为 null，则用户名已存在
    }

    public void inster(User newUser) {
        loginmapper.insert(newUser);
    }

    public Result login(@NotNull LoginRequest loginRequest) {
        // 通过AuthenticationManager的authenticate方法来进行用户认证
        MyUserDetails authenticate = loginmapper.loadUserByUsername(loginRequest.getUsername());
        // 判断是否验证成功
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 使用userid生成token
        String username = authenticate.getUsername();
        String jwt = jwtTokenProvider.generateToken(username);
        // userId用作key，将用户信息存入redis，并设置30分钟过期
        redisTemplate.opsForValue().set("login:" + jwt, authenticate, 30, TimeUnit.MINUTES);
        // 把token响应给前端
        return Result.success(jwt);
    }

    public Result logout(String token) {
        // 从Redis中删除登录信息
        try {
            Boolean isDeleted = redisTemplate.delete("login:" + token);
            if (isDeleted != null && isDeleted) {
                return Result.success("成功登出");
            } else {
                return Result.failure("登出失败或用户未登录");
            }
        } catch (Exception e) {
            // 处理异常
            return Result.failure("出现异常: " + e.getMessage());
        }
    }



}
