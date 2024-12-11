package air.admin.spring_boot.module.doctor.service;

import air.admin.spring_boot.module.doctor.dto.DoctorQueryDto;
import air.admin.spring_boot.module.doctor.dto.DoctorResultDto;
import air.admin.spring_boot.module.doctor.entity.DoctorEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface DoctorService extends IService<DoctorEntity> {

    /**
     * @param page
     * @param query
     * @return
     */
    IPage<DoctorResultDto> page(IPage<DoctorResultDto> page, DoctorQueryDto query);

    int insert(DoctorEntity doctor);

}
