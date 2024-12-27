package air.admin.spring_boot.login.service;

import air.admin.spring_boot.common.Security.config.JwtTokenProvider;
import air.admin.spring_boot.login.dto.LoginRequest;
import air.admin.spring_boot.login.entity.MyUserDetails;
import air.admin.spring_boot.login.entity.User;
import air.admin.spring_boot.login.mapper.Loginmapper;
import air.admin.spring_boot.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserService extends ServiceImpl<Loginmapper, User> {

    @Resource
    private Loginmapper loginmapper;

    @Resource
    private JwtTokenProvider jwtTokenProvider;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


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
        User statu = loginmapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username));
        String jwt = jwtTokenProvider.generateToken(username);
        // userId用作key，将用户信息存入redis，并设置30分钟过期
         redisTemplate.opsForValue().set(statu.getStatu()+":" + jwt, authenticate, 30, TimeUnit.MINUTES);
//        redisTemplate.opsForValue().set("login:" + jwt, authenticate);
        // 把token响应给前端
        return Result.success(jwt);
    }

    public Result logout(String token) {
        String username = jwtTokenProvider.getUsernameFromToken(token);
        User user = loginmapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username));
        // 从 Redis 中删除用户信息
        redisTemplate.delete(user.getStatu()+":" + token);
        return Result.success("退出登录成功");
    }


}
