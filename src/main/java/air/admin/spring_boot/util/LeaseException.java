package air.admin.spring_boot.util;

import air.admin.spring_boot.base.constant.ResultCodeEnum;

public class LeaseException extends Exception {
    private ResultCodeEnum errorCode;

    public LeaseException(ResultCodeEnum errorCode) {
        super(errorCode.getMessage()); // 调用父类构造函数
        this.errorCode = errorCode;
    }

    public ResultCodeEnum getErrorCode() {
        return errorCode;
    }
}

