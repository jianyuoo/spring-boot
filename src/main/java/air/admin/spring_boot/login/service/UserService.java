package air.admin.spring_boot.login.service;

import air.admin.spring_boot.login.entity.User;
import air.admin.spring_boot.login.mapper.loginmapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<loginmapper, User> {

    @Autowired
    private loginmapper loginmapper;

    public boolean existsByUsername(String username) {
        String user = loginmapper.findUsernameByUsername(username); // 查询用户名
        return user != null; // 如果 user 不为 null，则用户名已存在
    }
}
