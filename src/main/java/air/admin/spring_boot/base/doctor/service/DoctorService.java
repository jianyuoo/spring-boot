package air.admin.spring_boot.base.doctor.service;

import air.admin.spring_boot.base.doctor.dto.DoctorQueryDto;
import air.admin.spring_boot.base.doctor.dto.DoctorResultDto;
import air.admin.spring_boot.base.doctor.dto.DoctorSaveDto;
import air.admin.spring_boot.base.doctor.entity.Doctor;
import air.admin.spring_boot.base.doctor.mapper.DoctorMapper;
import air.admin.spring_boot.login.entity.User;
import air.admin.spring_boot.login.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DoctorService extends ServiceImpl<DoctorMapper, Doctor> {

    @Resource
    private DoctorMapper doctorMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
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

    public boolean set(@NotNull DoctorSaveDto dto , Doctor doctor) {
        User user = new User();
        BeanUtils.copyProperties(dto, doctor);
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(Password(dto.getId()));
        user.setStatu("医生");
        userService.save(user);
        return save(doctor);
    }
}
