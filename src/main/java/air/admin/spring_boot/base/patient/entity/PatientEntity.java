package air.admin.spring_boot.base.patient.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName(value = "mzgl_patient")
public class PatientEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * patient_id
     */
    @TableField(value = "id")
    private Long id;

    /**
     * name
     */
    @TableField(value = "name")
    private String name;


    @TableField(value = "username")
    private String username;

    /**
     * age
     */
    @TableField(value = "age")
    private String age;

    /**
     * sex
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * cases_id
     */
    @TableField(value = "cases_id")
    private String casesId;

    /**
     * hospitalized_status
     */
    @TableField(value = "hospitalized_status")
    private boolean hospitalizedStatus;

}
