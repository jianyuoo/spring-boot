package air.admin.spring_boot.login.mapper;

import air.admin.spring_boot.login.entity.User;
import air.admin.spring_boot.login.vo.LoginResVo;
import air.admin.spring_boot.login.vo.RegisterResVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;



/**
 * mapstruct 工具类定义步骤：
 * 1、添加MapStruct jar包依赖
 * 2、新增接口或抽象类，并且使用org.mapstruct.Mapper注解标签修饰。
 * 3、添加自定义转换方法
 */

/**
 * 用户实体类 各种数据转换
 */
@Mapper(componentModel = "spring")
public interface UserConvert{
    UserConvert INSTANCE = Mappers.getMapper( UserConvert.class );

    /* 实体类 转 登录响应结果数据对象 */
    LoginResVo toLoginRes(User user);

    /*  登录响应结果数据对象 转 实体类  */
    User toUserEntity(LoginResVo loginRes);

    RegisterResVo toRegisterResVo(User user);

    User toUser(RegisterResVo registerResVo);

}
