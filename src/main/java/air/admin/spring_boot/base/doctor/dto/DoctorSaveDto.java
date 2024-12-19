package air.admin.spring_boot.base.doctor.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class DoctorSaveDto {
    @TableField(value = "doctor_id")
    private Long  doctorid;

    @TableField(value = "doctor_name")
    private String name;

    @TableField(value = "age")
    private String age;

    @TableField(value = "sex")
    private String sex;

    @TableField(value = "department_id")
    private String departmentid;

    @TableField(value = "jobtitle")
    private String jobtitle;
}
