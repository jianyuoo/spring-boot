package air.admin.spring_boot.base.patient.service;

import air.admin.spring_boot.base.patient.dto.PatientQueryDto;
import air.admin.spring_boot.base.patient.dto.PatientResultDto;
import air.admin.spring_boot.base.patient.entity.PatientEntity;
import air.admin.spring_boot.base.patient.mapper.PatientMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService extends ServiceImpl<PatientMapper, PatientEntity> {

    @Autowired
    private PatientMapper patientMapper;

    public PatientResultDto getAllPatient(PatientQueryDto dto) {
        return patientMapper.getAllPatient(dto);
    }
}
