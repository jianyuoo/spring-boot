package air.admin.spring_boot.base.nurse.service;

import air.admin.spring_boot.base.nurse.Mapper.NurseMapper;
import air.admin.spring_boot.base.nurse.dto.NurseQueryDto;
import air.admin.spring_boot.base.nurse.dto.NurseResultDto;
import air.admin.spring_boot.base.nurse.dto.NurseSaveDto;
import air.admin.spring_boot.base.nurse.entity.NurseEntity;
import air.admin.spring_boot.login.entity.User;
import air.admin.spring_boot.login.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class NurseService extends ServiceImpl<NurseMapper, NurseEntity> {

    @Autowired
    private NurseMapper nurseMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;


    public NurseResultDto getAllNurse(NurseQueryDto dto) {
        return nurseMapper.getAllNurse(dto);
    }

    public String Password(Long id) {
        // 获取用户ID，并将其转换为字符串
        String userIdStr = String.valueOf(id);
        // 获取ID的后六位
        String password = userIdStr.length() > 6 ? userIdStr.substring(userIdStr.length() - 6) : userIdStr;
        //加密用户密码
        password = passwordEncoder.encode(password);
        // 设置用户密码
        return password;
    }

    public boolean set(@NotNull NurseSaveDto dto , NurseEntity nurse) {
        User user = new User();
        BeanUtils.copyProperties(dto, nurse);
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(Password(dto.getId()));
        userService.save(user);
        return save(nurse);
    }


}
