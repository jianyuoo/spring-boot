package air.admin.spring_boot.base.doctor.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "mzgl_doctor")
public class Doctor{

    @TableField(value = "id")
    private Long  id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "username")
    private String username;

    @TableField(value = "doctor_age")
    private String age;

    @TableField(value = "doctor_sex")
    private String sex;

    @TableField(value = "department_id")
    private String departmentid;

    @TableField(value = "jobtitle")
    private String jobtitle;
}
