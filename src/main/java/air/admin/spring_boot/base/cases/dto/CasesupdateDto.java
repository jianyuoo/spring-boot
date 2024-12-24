package air.admin.spring_boot.base.cases.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CasesupdateDto {
    /*
     *  病例id
     * */
    private Long caseid;

    /*
     *  病例名称
     * */
    private String diseasename;

    /*
     *  病例修改时间
     * */
    private LocalDateTime updatetime;

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
