package air.admin.spring_boot.base.constant;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    ADMIN_LOGIN_AUTH("管理员登录认证失败"),
    TOKEN_EXPIRED("令牌已过期"),
    TOKEN_INVALID("令牌无效"),
    ADMIN_CAPTCHA_CODE_NOT_FOUND("验证码未找到"),
    ADMIN_CAPTCHA_CODE_EXPIRED("验证码已过期"),
    ADMIN_CAPTCHA_CODE_ERROR("验证码错误"),
    ADMIN_ACCOUNT_NOT_EXIST_ERROR("账户不存在"),
    ADMIN_ACCOUNT_DISABLED_ERROR("账户已禁用"),
    ADMIN_ACCOUNT_ERROR("账户或密码错误");

    private final String message;

    ResultCodeEnum(String message) {
        this.message = message;
    }

}

