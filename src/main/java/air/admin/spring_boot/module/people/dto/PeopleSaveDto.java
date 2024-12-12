package air.admin.spring_boot.module.people.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;


@Data
public class PeopleSaveDto {
    /**
     * 病患信息保存
     */
    @TableField(value = "p_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @TableField(value = "p_name")
    private String name;
    @TableField(value = "p_age")
    private int age;
    @TableField(value = "p_gender")
    private String gender;
    @TableField(value = "p_contact")
    private String contact;
    @TableField(value = "p_address")
    private String address;
}
