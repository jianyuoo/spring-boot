package air.admin.spring_boot.base.cases.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CasesSaveDto {
    /*
          病例id
         */
    private Long caseid;
    /*
     *  病例名称
     * */
    private String name;
    /*
     *  患者id
     * */
    private Long patientid;
    /*
     *  医生id
     * */
    private Long doctorid;
    /*
     *  检查单id
     * */
    private Long examineid;
    /*
     *  病例建立时间
     * */
    private LocalDateTime casecreatetime;
    /*
     *  病例修改时间
     * */
    private LocalDateTime caseupdatetime;
    /*
     *  病例数据
     * */
    private String casedata;
    /*
     *  药品id
     * */
    private Long drugid;
    /*
     *  药品数据
     * */
    private String drugdata;
}
