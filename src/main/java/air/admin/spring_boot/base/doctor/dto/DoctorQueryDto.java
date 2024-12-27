package air.admin.spring_boot.base.doctor.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class DoctorQueryDto {

    @TableField(value = "id")
    private Long id;

    @TableField(value = "department_id")
    private Long departmentid;
}
