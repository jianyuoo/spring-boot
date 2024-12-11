package air.admin.spring_boot.module.login.vo;

import lombok.Data;

@Data
public class LoginVo {

    private String username;
    private String password;

    private String captchaKey;
    private String captchaCode;
}
