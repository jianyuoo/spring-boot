package air.admin.spring_boot.util.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginUser {

    private Long userId;
    private String username;
}
