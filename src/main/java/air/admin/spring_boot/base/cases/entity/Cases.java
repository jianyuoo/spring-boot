package air.admin.spring_boot.base.cases.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/*
 * 病例表实体类
 * */
@Data
@TableName(value = "mzgl_cases")
@AllArgsConstructor
@NoArgsConstructor
public class Cases {
    /*
     *  病例id
     * */
    @TableId
    private Long caseid;
    /*
     *  病例名称
     * */
    @TableField(value = "disease_name")
    private String diseasename;
    /*
     *  患者id
     * */
    @TableField(value = "patient_id")
    private Long patientid;
    /*
     *  医生id
     * */
    @TableField(value = "doctor_id")
    private Long doctorid;
    /*
     *  检查单id
     * */
    @TableField(value = "examine_id")
    private Long examineid;
    /*
     *  病例建立时间
     * */
    @TableField(value = "create_time")
    private LocalDateTime createtime;
    /*
     *  病例修改时间
     * */
    @TableField(value = "update_time")
    private LocalDateTime updatetime;
    /*
     *  病例数据
     * */
    @TableField(value = "cases_date")
    private String casedata;
    /*
     *  药品id
     * */
    @TableField(value = "drug_id")
    private Long drugid;
    /*
     *  药品数据
     * */
    @TableField(value = "drug_data")
    private String drugdata;
}
