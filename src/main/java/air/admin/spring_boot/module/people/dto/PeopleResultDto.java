package air.admin.spring_boot.module.people.dto;



@lombok.Data
public class PeopleResultDto {
    /**
     * 病患信息返回
     */
    private int id;
    private String name;
    private int age;
    private String gender;
    private String contact;
    private String address;
}
