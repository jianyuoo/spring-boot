package air.admin.spring_boot.base.patient.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class PatientSaveDto {
    /**
     * patient_id
     */
    private Long id;

    /**
     * name
     */
    private String name;


    private String username;

    /**
     * age
     */
    private String age;

    /**
     * sex
     */
    private String sex;

    /**
     * cases_id
     */
    private String casesid;

    /**
     * hospitalized_status
     */
    private boolean hospitalizedStatus;
}
