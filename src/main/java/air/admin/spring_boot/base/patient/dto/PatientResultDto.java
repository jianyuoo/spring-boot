package air.admin.spring_boot.base.patient.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PatientResultDto {
    /**
     * patient_id
     */
    @TableField(value = "patient_id")
    private Long patientId;

    /**
     * name
     */
    @TableField(value = "name")
    private String name;

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
     * data_time
     */
    @TableField(value = "data_time")
    private LocalDateTime dataTime;

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
