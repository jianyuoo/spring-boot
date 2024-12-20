package air.admin.spring_boot.module.registered.controller;

import air.admin.spring_boot.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class register {

    /*挂号服务*/
    @PostMapping("/create")
    public Result createRegister(){

        return Result.success();
    }
}
