package air.admin.spring_boot.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import air.admin.spring_boot.login.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {

}
