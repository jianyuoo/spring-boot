package air.admin.spring_boot.login.service;

import air.admin.spring_boot.base.doctor.dto.DoctorSaveDto;
import air.admin.spring_boot.login.entity.User;
import air.admin.spring_boot.login.mapper.loginmapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Select;
import org.jetbrains.annotations.NotNull;
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

    public void inster(User newUser) {
        loginmapper.insert(newUser);
    }

    public String setUserPassword(Long id) {
        // 获取用户ID，并将其转换为字符串
        String userIdStr = String.valueOf(id);
        // 获取ID的后六位
        String password = userIdStr.length() > 6 ? userIdStr.substring(userIdStr.length() - 6) : userIdStr;
        // 设置用户密码
        return password;
    }

    public boolean setdoctor(@NotNull DoctorSaveDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(setUserPassword(dto.getId()));
        return save(user);
    }
}
