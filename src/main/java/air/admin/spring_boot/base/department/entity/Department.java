package air.admin.spring_boot.base.department.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@TableName(value = "mzgl_department")
public class Department {

    /*部门id*/
    @TableId
    private Long departmentid;

    /*部门名称*/
    @TableField(value = "department")
    private String department;

    /*负责人id*/
    @TableField(value = "responsible")
    private String responsible;

    /*负责人名称*/
    @TableField(value = "responsible_id")
    public Long responsibleid;
}
