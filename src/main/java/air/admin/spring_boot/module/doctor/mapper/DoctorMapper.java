package air.admin.spring_boot.module.doctor.mapper;

import air.admin.spring_boot.module.doctor.entity.DoctorEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface DoctorMapper extends BaseMapper<DoctorEntity> {

}
