package air.admin.spring_boot.module.login.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CaptchaVo {

    private String image;

    private String key;

}
