package air.admin.spring_boot.module.doctor.dto;

import air.admin.spring_boot.util.PageParam;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
/**
 *  医生信息查询类
 **/
@Data
public class DoctorQueryDto extends PageParam {
    /**
     * 医生id
     **/
    private Long doctorId;

    /**
     * 医生姓名
     **/
    private String doctorName;

    /**
     * 部门
     **/
    private String doctorDepartment;

}
