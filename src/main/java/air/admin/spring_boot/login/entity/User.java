package air.admin.spring_boot.login.entity;



import com.baomidou.mybatisplus.annotation.TableName;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;


/**
 * 用户表
 * */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户对象")
@TableName("mzgl_user")
public class User{

    private String username; //账号
    private String name; //姓名
    private String password; //密码


}
