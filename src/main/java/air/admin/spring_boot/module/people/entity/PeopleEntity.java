package air.admin.spring_boot.module.people.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "base_people")
public class PeopleEntity implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 病患id
     */
    @TableField(value = "id")
    private int id;
    /**
     * 病患姓名
     */
    @TableField(value = "name")
    private String name;
    /**
     * 病患年龄
     */
    @TableField(value = "age")
    private int age;
    /**
     * 病患性别
     */
    @TableField(value = "gender")
    private String gender;
    /**
     * 病患联系电话
     */
    @TableField(value = "contact")
    private String contact;
    /**
     * 病患住址
     */
    @TableField(value = "address")
    private String address;

}
