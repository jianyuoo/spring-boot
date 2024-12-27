package air.admin.spring_boot.base.cases.dto;

import lombok.Data;



@Data
public class CasesQueryDto {

    /*
     *  患者id
     * */
    private Long patientid;

    /*
     *  医生id
     * */
    private Long doctorid;

}
