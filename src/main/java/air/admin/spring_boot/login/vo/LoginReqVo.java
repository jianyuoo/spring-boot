package air.admin.spring_boot.login.vo;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Size;
import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;


/**
 * 登录请求传入的用户数据
 */
@Data
@Schema(description = "用户登录对象")
public class LoginReqVo {

    @NotBlank(message = "用户名不能为空")
    //@Schema(description = "用户名",required = true) // required = true弃用了
    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码不能少于6位")
    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

}
