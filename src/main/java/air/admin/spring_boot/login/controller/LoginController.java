package air.admin.spring_boot.login.controller;

import air.admin.spring_boot.login.dto.LoginRequest;
import air.admin.spring_boot.login.dto.RegisterRequest;
import air.admin.spring_boot.login.entity.MyUserDetails;
import air.admin.spring_boot.login.entity.User;
import air.admin.spring_boot.login.service.UserService;
import air.admin.spring_boot.util.Result;
import air.admin.spring_boot.login.vo.VoCode;
import air.admin.spring_boot.util.Security.config.JwtTokenProvider;
import air.admin.spring_boot.util.Security.service.CustomerUserDetailsService;
import cn.hutool.core.lang.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
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
    public Result<String> login(@RequestBody LoginRequest loginRequest) {
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

    @PostMapping("/register")
    public Result register(@RequestBody RegisterRequest registerRequest) {
        // 检查用户名是否已存在
        if (userService.existsByUsername(registerRequest.getUsername())) {
            return Result.failure("用户名已存在");
        }

        // 创建新用户
        User newUser = new User();
        newUser.setName(registerRequest.getName());
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // 密码加密

        // 保存用户到数据库
        userService.save(newUser);

        return Result.success("注册成功");
    }

    @Operation(summary = "获取验证码")
    @GetMapping("/captcha")
    public Result getCaptcha(){
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 50, 4, 2);
        String codeValue = circleCaptcha.getCode();
        String imageBase64 = circleCaptcha.getImageBase64();

        String codeKey = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(codeKey,codeValue,5, TimeUnit.MINUTES);
        VoCode voCode=new VoCode(codeKey,"data:images/png;base64,"+imageBase64);
        return Result.successData(voCode);


    }


}

