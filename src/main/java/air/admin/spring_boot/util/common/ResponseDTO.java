//package air.admin.spring_boot.util.common;
//
//
//import lombok.Data;
//import lombok.Getter;
//
//
//
//import java.util.List;
//
///**
// * 请求返回对象
// *
// */
//@Getter
//@Data
//public class ResponseDTO<T> {
//    //current   size  records
//    public static final int OK_CODE = 0;
//
//    public static final String OK_MSG = "操作成功";
//
//    private String msg;
//
//    private Boolean ok;
//
//    private T data;
//
//    private Long current;
//
//    private Long size;
//
//    private List<T> records;
//
//    public ResponseDTO(Integer code, boolean ok, String msg, T data) {
//        this.ok = ok;
//        this.msg = msg;
//        this.data = data;
//    }
//
//    public ResponseDTO(int okCode, long current, long size, String okMsg, IPage<T> page) {
//        this.ok = true;
//        this.msg = okMsg;
//        this.current = current;
//        this.size = size;
//        this.records = page.getRecords(); // 将 IPage 中的记录赋值给 records
//    }
//
//
//    public static <T> ResponseDTO<T> ok() {
//        return new ResponseDTO<>(OK_CODE,true, OK_MSG, null);
//    }
//
//    public static <T> ResponseDTO<T> ok(T data) {
//        if (data instanceof IPage) {
//            IPage<T> page = (IPage<T>) data;
//            return new ResponseDTO<>(OK_CODE, page.getCurrent(), page.getSize(), OK_MSG, page);
//        } else {
//            return new ResponseDTO<>(OK_CODE,true, OK_MSG, data);
//        }
//    }
//
//}
