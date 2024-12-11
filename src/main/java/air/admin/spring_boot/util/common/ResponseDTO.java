package air.admin.spring_boot.util.common;

import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 请求返回对象
 *
 */
@Getter
@Data
public class ResponseDTO<T> {

    public static final int OK_CODE = 0;

    public static final String OK_MSG = "操作成功";

    private String msg;

    private Boolean ok;

    private T data;


    public ResponseDTO(Integer code, String level, boolean ok, String msg, T data) {
        this.ok = ok;
        this.msg = msg;
        this.data = data;
    }

    public ResponseDTO(Integer code, String level, boolean ok, String msg) {
        this.ok = ok;
        this.msg = msg;
    }

    public ResponseDTO(boolean ok, String msg, T data) {
        this.ok = ok;
        if (StringUtils.isNotBlank(msg)) {
            this.msg = msg;
        }
        this.data = data;
    }

    public static <T> ResponseDTO<T> ok() {
        return new ResponseDTO<>(OK_CODE, null, true, OK_MSG, null);
    }

    public static <T> ResponseDTO<T> ok(T data) {
        return new ResponseDTO<>(OK_CODE, null, true, OK_MSG, data);
    }

}
