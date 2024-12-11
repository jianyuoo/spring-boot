package air.admin.spring_boot.module.people.dto;

import air.admin.spring_boot.util.PageParam;


/**
 * 病患信息查询
 */
@lombok.Data
public class PeopleQueryDto extends PageParam {
    /**
     * 病患的基本信息
     */
    private int id;
    private String name;
    private int age;
    private String gender;
    private String contact;
    private String address;
}
