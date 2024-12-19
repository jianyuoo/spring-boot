package air.admin.spring_boot.base.nurse.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class NurseResultDto {
    @TableField(value = "id")
    private Long id;//护士id

    @TableField(value = "name")
    private String name;//护士姓名

    @TableField(value = "jobtitle")
    private String jobtitle;//护士职称

    @TableField(value = "department_id")
    private Long departmentId;//部门id
}
