package air.admin.spring_boot.module.doctor.service.impl;

import air.admin.spring_boot.module.doctor.dto.DoctorQueryDto;
import air.admin.spring_boot.module.doctor.dto.DoctorResultDto;
import air.admin.spring_boot.module.doctor.entity.DoctorEntity;
import air.admin.spring_boot.module.doctor.mapper.DoctorMapper;
import air.admin.spring_boot.module.doctor.service.DoctorService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, DoctorEntity>
        implements DoctorService {


    @Override
    public IPage<DoctorResultDto> page(IPage<DoctorResultDto> page, DoctorQueryDto query) {
        return this.baseMapper.page(page, query);
    }

    @Override
    public int insert(DoctorEntity doctor) {
        return this.baseMapper.insert(doctor);
    }

}
