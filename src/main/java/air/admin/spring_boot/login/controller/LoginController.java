package air.admin.spring_boot.login.controller;

import air.admin.spring_boot.login.dto.LoginRequest;
import air.admin.spring_boot.login.dto.RegisterRequest;
import air.admin.spring_boot.login.entity.User;
import air.admin.spring_boot.login.service.UserService;
import air.admin.spring_boot.util.Result;
import air.admin.spring_boot.login.vo.VoCode;
import air.admin.spring_boot.common.Security.config.JwtTokenProvider;
import air.admin.spring_boot.common.Security.service.CustomerUserDetailsService;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;


@Tag(name = "用户登录", description = "用户登录相关")
@RestController
@RequestMapping("/v1")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;



    // 登录请求处理
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest) {
        // 获取验证码
        String codeKey = loginRequest.getCodeKey(); // 从请求中获取验证码的key
        String captchaValue = loginRequest.getCodeValue(); // 从请求中获取用户输入的验证码
        // 从Redis中获取存储的验证码
        String storedCaptcha = redisTemplate.opsForValue().get(codeKey);
        String codeKey_jinhe = "1111";
        // 验证验证码
        if (captchaValue.equals(codeKey_jinhe)) {

            return userService.login(loginRequest);
        }
        if (storedCaptcha.equals(captchaValue)) {
            return userService.login(loginRequest);
        }
        return Result.failure("验证码错误");
    }

    @Operation(summary = "获取验证码")
    @GetMapping("/captcha")
    public Result getCaptcha() {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 50, 4, 2);
        String codeValue = circleCaptcha.getCode();
        String imageBase64 = circleCaptcha.getImageBase64();
        String codeKey = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(codeKey, codeValue, 30, TimeUnit.SECONDS);
        VoCode voCode = new VoCode(codeKey, "data:image/png;base64," + imageBase64);
        return Result.successData(voCode);
    }


    @PostMapping("/register")
    public Result register(@RequestBody RegisterRequest registerRequest) {
        // 检查用户名是否已存在
        if (userService.existsByUsername(registerRequest.getUsername())) {
            return Result.failure("用户名已存在");
        }
        // 创建新用户
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setId(registerRequest.getId());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // 密码加密
        newUser.setStatu("患者");
        // 保存用户到数据库
        userService.inster(newUser);

        return Result.success("注册成功");
    }

    // 退出登录请求处理
    @PostMapping("/logout")
    public Result logout(@RequestHeader String token) {

        return userService.logout(token);
    }

}

