package air.admin.spring_boot.login.controller;

import air.admin.spring_boot.config.vo.Result;
import air.admin.spring_boot.login.service.UserService;
import air.admin.spring_boot.login.service.impl.CaptchaService;
import air.admin.spring_boot.login.vo.LoginReqVo;
import air.admin.spring_boot.login.vo.RegisterReqVo;
import air.admin.spring_boot.login.vo.VoCode;
import cn.hutool.core.lang.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;


@Tag(name = "用户登录", description = "用户登录相关")
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginReqVo login) {
        return Result.success(userService.login(login));
    }

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterReqVo register) {
        //加入默认权限
        //
        return Result.success(userService.register(register));
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

