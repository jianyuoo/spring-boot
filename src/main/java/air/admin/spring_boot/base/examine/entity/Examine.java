package air.admin.spring_boot.base.examine.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
/**
    检查单
*/
@Data
@TableName(value = "mzgl_examine")
public class Examine {

    @TableId
    private Long examineid;

    @TableField(value = "examineitem")
    private String examineitem;

    @TableField(value = "examineid_time")
    private LocalDateTime examineidtime;

    @TableField(value = "examineid_data")
    private String examineiddata;
}
