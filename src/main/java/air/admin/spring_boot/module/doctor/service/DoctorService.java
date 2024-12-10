package air.admin.spring_boot.module.doctor.service;

import air.admin.spring_boot.module.doctor.dto.DoctorQueryDto;
import air.admin.spring_boot.module.doctor.dto.DoctorResultDto;
import air.admin.spring_boot.module.doctor.entity.DoctorEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

public interface DoctorService extends IService<DoctorEntity> {

    IPage<DoctorResultDto> page(IPage<DoctorResultDto>page, DoctorQueryDto query);
}
