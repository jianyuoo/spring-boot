package air.admin.spring_boot.module.doctor.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "base_doctor")
public class DoctorEntity implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 医生id
    **/
    @TableField(value = "doctor_id")
    private Long doctorId;

    /**
     * 医生姓名
     **/
    @TableField(value = "doctor_name")
    private String doctorName;
    /**
     *医生年龄
     **/
    @TableField(value = "doctor_age")
    private Integer doctorAge;

    /**
     * 医生性别
     **/
    @TableField(value = "doctor_sex")
    private String doctorSex;

    /**
     * 部门id
     **/
    @TableField(value = "doctor_department_id")
    private Long doctorDepartmentId;

    /**
     * 部门
     **/
    @TableField(value = "doctor_department")
    private String doctorDepartment;

    /**
     * 职称
     **/
    @TableField(value = "doctor_job_title")
    private String doctorJobTitle;

}
