package air.admin.spring_boot.base.drug.dto;

import lombok.Data;

@Data
public class DrugUpdataDto {

    /**
     *  药品价格
     * */
    private Integer drugprice;

    /**
     *  药品照片
     * */
    private String drugurl;
}
