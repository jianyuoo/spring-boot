package air.admin.spring_boot.module.registered.controller;

import air.admin.spring_boot.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class register {

    /*挂号服务*/
    @PostMapping("/create")
    public Result createRegister(){
        // 1.创建病例与检查单

        return Result.success();
    }
    @GetMapping("/gettoken")
    public String getToken(HttpServletRequest request) {
        // 从请求头中获取 Token
        String token = request.getHeader("token");
        // 校验 Token 是否以 "Bearer " 开头
        if (token != null) {
            // 去掉 "Bearer " 前缀
            token = token.substring(7);
            System.out.println(token);
            return token;
        } else {
            return null;
        }
    }
}
