package air.admin.spring_boot.module.doctor.dto;

import lombok.Data;

/**
 *  医生信息保存类
 **/
@Data
public class DoctorSaveDto {

    /**
     * 医生id
     **/
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
