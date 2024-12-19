package air.admin.spring_boot.base.nurse.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "mzgl_nurse")
public class NurseEntity {

    @TableField(value = "id")
    private Long id;//护士id

    @TableField(value = "name")
    private String name;//护士姓名

    @TableField(value = "jobtitle")
    private String jobtitle;//护士职称

    @TableField(value = "department_id")
    private Long departmentId;//部门id

}
