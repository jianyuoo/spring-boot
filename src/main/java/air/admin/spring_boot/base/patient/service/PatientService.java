package air.admin.spring_boot.base.patient.service;

import air.admin.spring_boot.base.doctor.dto.DoctorSaveDto;
import air.admin.spring_boot.base.doctor.entity.Doctor;
import air.admin.spring_boot.base.patient.dto.PatientQueryDto;
import air.admin.spring_boot.base.patient.dto.PatientResultDto;
import air.admin.spring_boot.base.patient.dto.PatientSaveDto;
import air.admin.spring_boot.base.patient.entity.PatientEntity;
import air.admin.spring_boot.base.patient.mapper.PatientMapper;
import air.admin.spring_boot.login.entity.User;
import air.admin.spring_boot.login.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PatientService extends ServiceImpl<PatientMapper, PatientEntity> {

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PatientResultDto getAllPatient(PatientQueryDto dto) {
        return patientMapper.getAllPatient(dto);
    }

    public void delete(@Valid PatientQueryDto dto) {
        patientMapper.delete(dto);
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

    public boolean set(@NotNull PatientSaveDto dto , PatientEntity patient) {
        User user = new User();
        BeanUtils.copyProperties(dto, patient);
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(Password(dto.getId()));
        userService.save(user);
        return save(patient);
    }
}
