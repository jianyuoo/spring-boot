package air.admin.spring_boot.util.common.enumeration;


import lombok.Getter;

@Getter
public enum DataTypeEnum implements BaseEnum {

    /**
     *普通数据
     */
    NORMAL(1, "普通数据"),

    /**
     * 加密数据
     */
    ENCRYPT(10,"加密数据" ),
    ;
    private final Integer value;

    private final String desc;

    DataTypeEnum(Integer value, String desc) {

        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }

}
