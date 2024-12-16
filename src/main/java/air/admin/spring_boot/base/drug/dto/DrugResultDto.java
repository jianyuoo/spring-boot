package air.admin.spring_boot.base.drug.dto;

import lombok.Data;

@Data
public class DrugResultDto {

    /**
     *  药品id
     * */
    private Integer drugid;

    /**
     *  药品名字
     * */
    private String drugname;

    /**
     *  药品用途
     * */
    private String drugusedisease;

    /**
     *  药品照片
     * */
    private String drugurl;

    /**
     *  药品价格
     * */
    private Integer drugprice;

    /**
     *  药品制作厂
     * */
    private String drugtype;
}
