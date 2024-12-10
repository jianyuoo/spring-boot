package air.admin.spring_boot.module.doctor.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 *  医生信息返回类
 **/
@Data
public class DoctorResultDto {
    /**
     * 医生id
     **/
    @JsonSerialize(using = ToStringSerializer.class)
    private Long doctorId;

    /**
     * 医生姓名
     **/
    private String doctorName;
    /**
     *医生年龄
     **/
    private Integer doctorAge;

    /**
     * 医生性别
     **/
    private String doctorSex;

    /**
     * 部门id
     **/
    @JsonSerialize(using = ToStringSerializer.class)
    private Long doctorDepartmentId;

    /**
     * 部门
     **/
    private String doctorDepartment;

    /**
     * 职称
     **/
    private String doctorJobTitle;
}
