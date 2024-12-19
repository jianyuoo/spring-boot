package air.admin.spring_boot.base.doctor.service;

import air.admin.spring_boot.base.doctor.dto.DoctorQueryDto;
import air.admin.spring_boot.base.doctor.entity.Doctor;
import air.admin.spring_boot.base.doctor.mapper.DoctorMapper;
import air.admin.spring_boot.util.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService extends ServiceImpl<DoctorMapper, Doctor> {

    @Autowired
    private DoctorMapper doctorMapper;

    public Result select(DoctorQueryDto dto) {
        return doctorMapper.select(dto);
    }
}
