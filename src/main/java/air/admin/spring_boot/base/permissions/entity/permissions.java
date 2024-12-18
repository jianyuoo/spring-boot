package air.admin.spring_boot.base.permissions.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class permissions {

    @TableField(value = "Permissions_id")
    private int id;

    @TableField(value = "Permissions")
    private String permissions;
}
