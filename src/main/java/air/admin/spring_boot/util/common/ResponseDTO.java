package air.admin.spring_boot.util.common;

import air.admin.spring_boot.module.doctor.dto.DoctorResultDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 请求返回对象
 *
 */
@Getter
@Data
public class ResponseDTO<T> {
    //current   size  records
    public static final int OK_CODE = 0;

    public static final String OK_MSG = "操作成功";

    private String msg;

    private Boolean ok;

    private T data;

    private Long current;

    private Long size;

    private List<T> records;

    public ResponseDTO(Integer code, boolean ok, String msg, T data) {
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

    public ResponseDTO(int ok ,Long current, Long size,String msg, List<T> records) {
        this.ok = true;
        this.msg = msg;
        this.current = current;
        this.size = size;
        this.records = records;
    }

    public ResponseDTO(int okCode, T data) {
        this.ok = true;
        this.msg = "OK";
        this.records = Collections.singletonList(data); // 将单个数据包装成列表
    }

    public ResponseDTO(int okCode, long current, long size, String okMsg, IPage<T> page) {
        this.ok = true;
        this.msg = okMsg;
        this.current = current;
        this.size = size;
        this.records = page.getRecords(); // 将 IPage 中的记录赋值给 records
    }


    public static <T> ResponseDTO<T> ok() {
        return new ResponseDTO<>(OK_CODE,true, OK_MSG, null);
    }

    public static <T> ResponseDTO<T> ok(T data) {
        if (data instanceof IPage) {
            IPage<T> page = (IPage<T>) data;
            return new ResponseDTO<>(OK_CODE, page.getCurrent(), page.getSize(), OK_MSG, page);
        } else {
            return new ResponseDTO<>(OK_CODE, data);
        }
    }

}
