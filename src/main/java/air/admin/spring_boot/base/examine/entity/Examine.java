package air.admin.spring_boot.base.examine.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
/**
    检查单
*/
@Data
@TableName(value = "mzgl_examine")
public class Examine {

    @TableField(value = "examine_id")
    private Long examineid;

    @TableField(value = "examineitem")
    private String examineitem;

    @TableField(value = "examineid_time")
    private LocalDateTime examineidtime;

    @TableField(value = "examineid_data")
    private String examineiddata;
}
