package air.admin.spring_boot.module.people.dto;

import air.admin.spring_boot.util.PageParam;
import lombok.Data;


/**
 * 病患信息查询
 */
@Data
public class PeopleQueryDto extends PageParam {
    /**
     * 病患的基本信息
     */
    private Long id;

    private String name;

}
