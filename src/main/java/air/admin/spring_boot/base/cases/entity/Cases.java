package air.admin.spring_boot.base.cases.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
/*
 * 病例表实体类
 * */
@Data
@TableName(value = "mzgl_cases")
public class Cases {
    /*
     *  病例id
     * */
    private Long caseid;
    /*
     *  病例名称
     * */
    private String diseasename;
    /*
     *  患者id
     * */
    private Long patient_username;
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
