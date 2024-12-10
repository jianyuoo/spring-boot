package air.admin.spring_boot.util.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {



    PARAM_ERROR(30001, "参数错误"),

    DATA_NOT_EXIST(30002, "数据不存在"),

    ALREADY_EXIST(30003, "数据已存在了呀~"),

    REPEAT_SUBMIT(30004, "亲~您操作的太快了，请稍等下再操作~"),

    NO_PERMISSION(30005, "对不起，您没有权限访问此内容哦~"),

    DEVELOPING(30006, "系統正在紧急开发中，敬请期待~"),

    LOGIN_STATE_INVALID(30007, "您还未登录或登录失效，请重新登录！"),

    USER_STATUS_ERROR(30008, "用户状态异常"),

    FORM_REPEAT_SUBMIT(30009, "请勿重复提交"),

    LOGIN_FAIL_LOCK(30010, "登录连续失败已经被锁定，无法登录"),
    LOGIN_FAIL_WILL_LOCK(30011, "登录连续失败将会锁定提醒"),

    LOGIN_ACTIVE_TIMEOUT(30012, "长时间未操作系统，需要重新登录"),

    XMGL_ERROR_INFO(30013, "项目管理-系统错误信息");

    private final int code;

    private final String msg;

    private final String level;

    UserErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.level = LEVEL_USER;
    }
    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getLevel() {
        return LEVEL_SYSTEM; // 确保此处返回正确等级
    }
}
