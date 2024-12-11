package air.admin.spring_boot.module.login.mapper;

import air.admin.spring_boot.module.login.entity.SystemUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper extends BaseMapper<SystemUserEntity> {

    SystemUserEntity selectById(Long userId);

    SystemUserEntity selectOneByUsername(String username);

    SystemUserEntity findUserByName(String username);

    void regist(SystemUserEntity systemUser);
}
