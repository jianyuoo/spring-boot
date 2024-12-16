package air.admin.spring_boot.login.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Schema(description = "用户登录成功后的数据对象")
public class RegisterResVo {


    @Schema(description = "用户名")
    private String username;


    @Schema(description = "用户创建时间")
    private LocalDateTime createtime;

    @Schema(description = "用户更新时间")
    private LocalDateTime updatetime;
}
