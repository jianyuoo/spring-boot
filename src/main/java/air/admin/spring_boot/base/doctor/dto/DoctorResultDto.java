package air.admin.spring_boot.base.doctor.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class DoctorResultDto {


    private Long  id;

    private String  password;

    private String name;

    private String age;

    private String sex;

    private String departmentid;

    private String jobtitle;
}
