package air.admin.spring_boot.module.login.controller;

import air.admin.spring_boot.module.login.entity.SystemUserEntity;
import air.admin.spring_boot.module.login.service.LoginService;
import air.admin.spring_boot.module.login.vo.CaptchaVo;
import air.admin.spring_boot.module.login.vo.LoginVo;
import air.admin.spring_boot.module.login.vo.SystemUserInfoVo;
import air.admin.spring_boot.util.JwtUtil;
import air.admin.spring_boot.util.LeaseException;
import air.admin.spring_boot.util.common.LoginUserHolder;
import air.admin.spring_boot.util.common.ResponseDTO;
import air.admin.spring_boot.util.common.Result;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/v1/user")
public class LoginController {
   @Autowired
    private LoginService loginService;

    @GetMapping("/graph-captcha")
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<CaptchaVo> getCapcha(){
        CaptchaVo captchaVo = loginService.getCaptcha();
        return ResponseDTO.ok(captchaVo);
    }
    @PostMapping("/regist")
    @Transactional(rollbackFor = Exception.class)
    public Result regist(@Valid @RequestBody SystemUserEntity systemUser){

        return loginService.regist(systemUser);
    }
    @PostMapping("/login")
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> login(@RequestBody LoginVo loginVo) throws LeaseException {
        String token = loginService.login(loginVo);
        return ResponseDTO.ok(token);
    }

    @GetMapping("info/token")
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<SystemUserInfoVo> infoWithToken(@RequestHeader("access-token") String token) throws LeaseException {
        Claims claims = JwtUtil.parseToken(token); // 解析JWT，获取声明
        Long userId = claims.get("userId", Long.class); // 从声明中获取用户ID
        SystemUserInfoVo userInfo = loginService.getLoginUserInfo(userId); // 获取用户信息
        return ResponseDTO.ok(userInfo); // 返回成功的响应
    }

    @GetMapping("info/current")
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<SystemUserInfoVo> info() {
        SystemUserInfoVo userInfo = loginService.getLoginUserInfo(LoginUserHolder.getLoginUser().getUserId()); // 从上下文获取当前用户ID
        return ResponseDTO.ok(userInfo); // 返回成功的响应
    }
}
