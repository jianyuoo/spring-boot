package air.admin.spring_boot.base.drug.dto;

import lombok.Data;

@Data
public class DrugQueryDto {
    /**
     *   药品名称
     * */
    private String drugname;
    /**
     *   药品制作厂
     * */
    private String drugtype;
}
