package air.admin.spring_boot.module.people.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;


@Data
public class PeopleSaveDto {
    /**
     * 病患信息保存
     */
    @TableField(value = "id")
    private int id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "age")
    private int age;
    @TableField(value = "gender")
    private String gender;
    @TableField(value = "contact")
    private String contact;
    @TableField(value = "address")
    private String address;
}
