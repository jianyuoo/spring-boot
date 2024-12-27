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

    /*
     * patient_id
     */
    @TableId
    private Long id;

    /*
     * name
     */
    @TableField(value = "name")
    private String name;

    //病例id
    @TableField(value = "cases_id")
    private Long casesid;


    @TableField(value = "username")
    private String username;

    /*
     * age
     */
    @TableField(value = "age")
    private String age;

    /*
     * sex
     */
    @TableField(value = "sex")
    private String sex;

    /*
     * hospitalized_status
     */
    @TableField(value = "hospitalized_status")
    private boolean hospitalizedStatus;

}
