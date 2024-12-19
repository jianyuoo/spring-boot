package air.admin.spring_boot.base.doctor.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class DoctorSaveDto {
    @TableField(value = "id")
    private Long  id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "username")
    private String username;
}
