package air.admin.spring_boot.base.cases.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CasesResultDto {
    /*
      病例id
     */
    private Long caseid;
    /*
     *  病例名称
     * */
    private String diseasename;
    /*
     *  患者名称
     * */
    private String patientname;
    /*
     *  医生名称
     * */
    private String doctorname;
    /*
     *  检查单id
     * */
    private Long examineid;
    /*
     *  病例建立时间
     * */
    private LocalDateTime createtime;
    /*
     *  病例修改时间
     * */
    private LocalDateTime updatetime;
    /*
     *  病例数据
     * */
    private String casesdate;
    /*
     *  药品名称
     * */
    private String drugname;
    /*
     *  药品数据
     * */
    private String drugdata;
}
