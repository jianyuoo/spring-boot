package air.admin.spring_boot.login.entity;



import com.baomidou.mybatisplus.annotation.TableName;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;




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


    private Long id; //主键ID
    private String username; //账号
    private String name; //姓名
    private String password; //密码


}
