package air.admin.spring_boot.login.controller;

import air.admin.spring_boot.config.vo.Result;
import air.admin.spring_boot.login.service.UserService;
import air.admin.spring_boot.login.service.impl.CaptchaService;
import air.admin.spring_boot.login.vo.LoginReqVo;
import air.admin.spring_boot.login.vo.RegisterReqVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Tag(name = "用户登录", description = "用户登录相关")
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    private CaptchaService captchaService;

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
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        captchaService.createCaptcha(request, response);
    }


}

