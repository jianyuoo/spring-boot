package air.admin.spring_boot.base.patient.mapper;

import air.admin.spring_boot.base.patient.dto.PatientQueryDto;
import air.admin.spring_boot.base.patient.dto.PatientResultDto;
import air.admin.spring_boot.base.patient.entity.PatientEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PatientMapper extends BaseMapper<PatientEntity> {
    PatientResultDto getAllPatient(PatientQueryDto dto);

    void delete(PatientQueryDto dto);
}
