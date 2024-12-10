package air.admin.spring_boot.module.doctor.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

@TableName(value = "doctor")
public class DoctorEntity implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}
