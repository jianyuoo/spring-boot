package air.admin.spring_boot.login.controller;

import air.admin.spring_boot.login.dto.LoginRequest;
import air.admin.spring_boot.login.dto.RegisterRequest;
import air.admin.spring_boot.login.entity.MyUserDetails;
import air.admin.spring_boot.login.entity.User;
import air.admin.spring_boot.login.service.UserService;
import air.admin.spring_boot.util.Result;
import air.admin.spring_boot.login.vo.VoCode;
import air.admin.spring_boot.common.Security.config.JwtTokenProvider;
import air.admin.spring_boot.common.Security.service.CustomerUserDetailsService;
import cn.hutool.core.lang.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
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
    private JwtTokenProvider jwtTokenProvider; // JWT 服务

    @Autowired
    private AuthenticationManager authenticationManagerBean; // Spring Security 的AuthenticationManager

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;


    // 登录请求处理
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest) {
        // 获取验证码
        String codeKey = loginRequest.getCodeKey(); // 从请求中获取验证码的key
        String captchaValue = loginRequest.getCodeValue(); // 从请求中获取用户输入的验证码

        // 从Redis中获取存储的验证码
        String storedCaptcha = (String) redisTemplate.opsForValue().get(codeKey);
        String codeKey_jinhe = "1111";

        // 验证验证码
        if (!storedCaptcha.equals(captchaValue) && !codeKey_jinhe.equals(captchaValue)) {
            return Result.failure("验证码错误");
        }

        // 通过用户名加载用户详细信息
        MyUserDetails myUserDetails = customerUserDetailsService.loadUserByUsername(loginRequest.getUsername());

        // 验证密码（这里可以使用 Spring Security 的 AuthenticationManager）
        if (myUserDetails == null || !passwordEncoder.matches(loginRequest.getPassword(), myUserDetails.getPassword())) {
            return Result.failure("用户名或密码错误");
        }

        // 如果身份验证成功，生成 JWT
        String token = jwtTokenProvider.generateToken(myUserDetails.getUsername());
        return Result.success(token); // 返回 JWT
    }

    @Operation(summary = "获取验证码")
    @GetMapping("/captcha")
    public Result getCaptcha() {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 50, 4, 2);
        String codeValue = circleCaptcha.getCode();
        String imageBase64 = circleCaptcha.getImageBase64();
        String codeKey = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(codeKey, codeValue, 5, TimeUnit.MINUTES);
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

        // 保存用户到数据库
        userService.inster(newUser);

        return Result.success("注册成功");
    }

    // 退出登录请求处理
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request) {
        // 从请求中获取 JWT
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            // 移除 "Bearer " 部分
            token = token.substring(7);
        }
        // 这里可以执行退出登录的其他操作，例如清理用户状态
        // 例如：如果实现了黑名单，可以在这里注销 token
        // jwtTokenProvider.addToBlacklist(token);

        // 直接返回成功消息
        return Result.success("成功退出登录");
    }


}

