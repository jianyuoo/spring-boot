package air.admin.spring_boot.module.people.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@lombok.Data
public class PeopleResultDto {
    /**
     * 病患信息返回
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    private int age;
    private String gender;
    private String contact;
    private String address;
}
