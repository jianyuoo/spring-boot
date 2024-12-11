package air.admin.spring_boot.module.doctor.mapper;

import air.admin.spring_boot.module.doctor.dto.DoctorQueryDto;
import air.admin.spring_boot.module.doctor.dto.DoctorResultDto;
import air.admin.spring_boot.module.doctor.entity.DoctorEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DoctorMapper extends BaseMapper<DoctorEntity> {

    /**
     * 查询分页
     */
    IPage<DoctorResultDto> page(IPage<DoctorResultDto> page, @Param("param") DoctorQueryDto query);

}
