package air.admin.spring_boot.login.mapper;

import air.admin.spring_boot.login.entity.MyUserDetails;
import air.admin.spring_boot.login.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Mapper
public interface Loginmapper extends UserDetails, BaseMapper<User>{

    MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    @Select("SELECT * FROM mzgl_user WHERE username = #{username}")
    User findUsernameByUsername(String username); // 返回查询的用户名
}
