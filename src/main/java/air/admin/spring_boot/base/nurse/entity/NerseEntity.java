package air.admin.spring_boot.base.nurse.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class NerseEntity {

    @TableField(value = "nurse_id")
    private int nurseId;//护士id

    @TableField(value = "nurse_name")
    private String nurseName;//护士姓名

    @TableField(value = "jobtitle")
    private String jobtitle;//护士职称

    @TableField(value = "department_id")
    private int departmentId;//部门id

}
