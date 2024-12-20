package air.admin.spring_boot.login.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;

    private String password;

    private String codeKey;

    public String codeValue;

}
