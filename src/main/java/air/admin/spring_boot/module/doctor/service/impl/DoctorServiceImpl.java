package air.admin.spring_boot.module.doctor.service.impl;

import air.admin.spring_boot.module.doctor.entity.DoctorEntity;
import air.admin.spring_boot.module.doctor.mapper.DoctorMapper;
import air.admin.spring_boot.module.doctor.service.DoctorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, DoctorEntity>
        implements DoctorService {

}
