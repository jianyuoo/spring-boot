package air.admin.spring_boot.base.doctor.mapper;

import air.admin.spring_boot.base.doctor.dto.DoctorQueryDto;
import air.admin.spring_boot.base.doctor.entity.Doctor;
import air.admin.spring_boot.util.Result;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DoctorMapper extends BaseMapper<Doctor> {

    Result select(DoctorQueryDto doctorid);
}
