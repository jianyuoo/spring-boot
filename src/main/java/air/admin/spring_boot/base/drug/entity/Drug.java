package air.admin.spring_boot.base.drug.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "mzgl_drug")
public class Drug {
    //药品id
    private int drugid;
    //药品名字
    private int drugname;
    //药品用途
    private String drugusedisease;
    //药品照片
    private String drugurl;

}
