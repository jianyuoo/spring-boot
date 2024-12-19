package air.admin.spring_boot.base.nurse.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class NurseQuenyDto {
    @TableField(value = "nurse_id")
    private int nurseId;//护士id
}
