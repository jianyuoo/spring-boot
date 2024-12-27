package air.admin.spring_boot.login.dto;

import lombok.Data;

@Data
public class LoginRequest {

    // 账户
    private String username;

    //密码
    private String password;

    // 验证码照片
    private String codeKey;

    //验证码
    public String codeValue;

    // 是否为医生
    public String statu;

}
