package air.admin.spring_boot.module.doctor.service;

import air.admin.spring_boot.module.doctor.dto.DoctorQueryDto;
import air.admin.spring_boot.module.doctor.dto.DoctorResultDto;
import air.admin.spring_boot.module.doctor.entity.DoctorEntity;
import air.admin.spring_boot.module.doctor.mapper.DoctorMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DoctorService extends ServiceImpl<DoctorMapper, DoctorEntity> {

    public IPage<DoctorResultDto> page(IPage<DoctorResultDto> page, DoctorQueryDto query) {
        return this.baseMapper.page(page, query);
    }

    public int insert(DoctorEntity doctor) {
        return this.baseMapper.insert(doctor);
    }

}
