package air.admin.spring_boot.login.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@Schema(description = "用户登录成功后的数据对象")
public class LoginResVo {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "token")
    private String token;

    @Schema(description = "用户创建时间")
    @TableField("create_time")
    private LocalDateTime createtime;

    @Schema(description = "用户更新时间")
    @TableField("update_time")
    private LocalDateTime updatetime;
}
