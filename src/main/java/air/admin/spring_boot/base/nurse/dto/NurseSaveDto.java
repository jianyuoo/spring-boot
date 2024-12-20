package air.admin.spring_boot.base.nurse.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class NurseSaveDto {

    private Long id;//护士id

    private String username; //护士账号


    private String name;//护士姓名


    private String jobtitle;//护士职称


    private Long departmentId;//部门id
}
