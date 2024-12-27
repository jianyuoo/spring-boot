package air.admin.spring_boot.base.drug.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "mzgl_drug")
public class Drug {
    /**
     *  药品id
     * */
    @TableId
    private Integer drugid;

    /**
     *  药品名字
     * */
    @TableField(value = "drug_name")
    private String drugname;

    /**
     *  药品用途
     * */
    @TableField(value = "drug_usedisease")
    private String drugusedisease;

    /**
     *  药品照片
     * */
    @TableField(value = "drugurl")
    private String drugurl;

    /**
     *  药品价格
     * */
    @TableField(value = "drug_price")
    private Integer drugprice;

    /**
     *  药品制作厂
     * */
    @TableField(value = "drug_type")
    private String drugtype;

}
