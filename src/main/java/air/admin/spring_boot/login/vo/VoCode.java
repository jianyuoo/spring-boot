package air.admin.spring_boot.login.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoCode {

    private String codeKey;

    private String codeValue;

}
