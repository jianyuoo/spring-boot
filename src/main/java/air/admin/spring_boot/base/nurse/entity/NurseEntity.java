package air.admin.spring_boot.base.nurse.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "mzgl_nurse")
public class NurseEntity {

    @TableField(value = "id")
    private int nurseId;//护士id

    @TableField(value = "name")
    private String nurseName;//护士姓名

    @TableField(value = "jobtitle")
    private String jobtitle;//护士职称

    @TableField(value = "department_id")
    private int departmentId;//部门id

}
