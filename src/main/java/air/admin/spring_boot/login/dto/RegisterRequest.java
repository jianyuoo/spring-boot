package air.admin.spring_boot.login.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private Long id;
    private String username;
    private String password;
}
