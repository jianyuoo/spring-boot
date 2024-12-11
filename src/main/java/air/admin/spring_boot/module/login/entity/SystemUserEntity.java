package air.admin.spring_boot.module.login.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "system_user")
public class SystemUserEntity {

    @TableField(value = "id")
    private Long id;

    @TableField(value = "user_name")
    private String username;

    @TableField(value = "avatarUrl")
    private String avatarUrl;

    @TableField(value = "name")
    private String name;

    @TableField(value = "password")
    private String password;

    @TableField(value = "status")
    private String status;

//    @TableField(value = "login_data")
//    private Date LoginData;
}
