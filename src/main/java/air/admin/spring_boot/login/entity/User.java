package air.admin.spring_boot.login.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Hidden;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 用户表
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "用户对象")
@TableName("mzgl_user")
public class User {
    @Schema(description = "用户id")
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Schema(description = "用户名")
    @TableField(value = "username")
    private String username;

    @Hidden
    @Schema(description = "密码")
    @TableField(value = "password")
    private String password;

    @Schema(description = "用户是否启用")
    @TableField(value = "enable")
    private boolean enable;

    @Schema(description = "用户是否删除")
    @TableField(value = "is_delete")
    private boolean isDelete;

    @Schema(description = "用户创建时间")
    @TableField(value = "create_time")
    private LocalDateTime createtime;

    @Schema(description = "用户更新时间")
    @TableField(value = "update_time")
    private LocalDateTime updatetime;

    @Schema(description = "用户身份")
    @TableField(value = "status")
    private String status;

}
