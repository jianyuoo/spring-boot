package air.admin.spring_boot.config.vo;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


import java.io.Serializable;

import static air.admin.spring_boot.util.enums.StatusCodeEnum.FAIL;
import static air.admin.spring_boot.util.enums.StatusCodeEnum.SUCCESS;

/**
 * 请求返回对象
 *
 */
@Schema(description = "结果返回类")
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 返回状态
     */
    @Schema(description = "返回状态")
    private Boolean flag;

    /**
     * 状态码
     */
    @Schema(description = "状态码")
    private Integer code;

    /**
     * 返回信息
     */
    @Schema(description = "返回信息")
    private String msg;

    /**
     * 返回数据
     */
    @Schema(description = "返回数据")
    private T data;
    /**
     * 返回异常
     */
    private Exception e;

    public Result(int i, String msg, T data) {
        this.code = i;
        this.msg = msg;
        this.data = data;
    }

    public Result() {

    }


    /**
     * 无参静态方法，用于创建一个表示成功结果的 Result 对象。
     * 返回一个 Result 对象，其中 flag 被设置为 true，code 被设置为成功状态码，msg 被设置为成功信息，而 data 为 null
     * */
    public static <T> Result<T> success() {
        return buildResult(true, null, SUCCESS.getCode(), SUCCESS.getMsg());
    }

    /**
     * 接受泛型参数 data，用于创建一个包含成功数据的 Result 对象。
     * 返回一个 Result 对象，其中 flag 被设置为 true，code 被设置为成功状态码，msg 被设置为成功信息，而 data 被设置为传入的数据。
     * */
    public static <T> Result<T> success(T data) {
        return buildResult(true, data, SUCCESS.getCode(), SUCCESS.getMsg());
    }

    /**
     * 接受一个字符串参数 message，用于创建一个表示失败结果的 Result 对象。
     * 返回一个 Result 对象，其中 flag 被设置为 false，code 被设置为失败状态码，msg 被设置为传入的消息，而 data 为 null。
     * */
    public static <T> Result<T> fail(String message) {
        return buildResult(false, null, FAIL.getCode(), message);
    }

    /**
     * 接受一个整数参数 code 和一个字符串参数 message，用于创建一个表示失败结果的 Result 对象。
     * 返回一个 Result 对象，其中 flag 被设置为 false，code 被设置为传入的状态码，msg 被设置为传入的消息，而 data 为 null。
     * */
    public static <T> Result<T> fail(Integer code, String message) {
        return buildResult(false, null, code, message);
    }

    /**
     * 私有静态方法，用于构建一个 Result 对象。
     * 接受 flag、data、code 和 message 四个参数，并返回一个根据这些参数构建的 Result 对象。
     * */
    private static <T> Result<T> buildResult(Boolean flag, T data, Integer code, String message) {
        Result<T> r = new Result<>();
        r.setFlag(flag);
        r.setData(data);
        r.setCode(code);
        r.setMsg(message);
        return r;
    }


    public static<T> Result<T> successData(T data){
        return new Result<>(200, "操作成功", data);
    }

    public static<T> Result<T> successMessage(String message){
        return new Result<>(200, message, null);
    }


    public static Result failure(String s) {
        return buildResult(false, s, FAIL.getCode(), FAIL.getMsg());
    }

}
