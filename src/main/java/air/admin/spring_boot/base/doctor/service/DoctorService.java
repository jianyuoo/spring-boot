package air.admin.spring_boot.base.doctor.service;

import air.admin.spring_boot.base.doctor.dto.DoctorQueryDto;
import air.admin.spring_boot.base.doctor.dto.DoctorResultDto;
import air.admin.spring_boot.base.doctor.dto.DoctorSaveDto;
import air.admin.spring_boot.base.doctor.entity.Doctor;
import air.admin.spring_boot.base.doctor.mapper.DoctorMapper;
import air.admin.spring_boot.login.entity.User;
import air.admin.spring_boot.login.service.UserService;
import air.admin.spring_boot.util.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService extends ServiceImpl<DoctorMapper, Doctor> {

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    public DoctorResultDto select(DoctorQueryDto dto) {
        return doctorMapper.select(dto);
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

    public boolean setdoctor(@NotNull DoctorSaveDto dto ,Doctor doctor) {
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(Password(dto.getId()));
        userService.save(user);
        return save(doctor);
    }
}
