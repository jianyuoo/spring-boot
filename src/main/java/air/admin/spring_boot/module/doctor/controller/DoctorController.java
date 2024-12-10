package air.admin.spring_boot.module.doctor.controller;

import air.admin.spring_boot.module.doctor.dto.DoctorSaveDto;
import air.admin.spring_boot.module.doctor.entity.DoctorEntity;
import air.admin.spring_boot.module.doctor.service.DoctorService;
import air.admin.spring_boot.util.common.ResponseDTO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/Doctor/")
public class DoctorController {

    @Resource
    private DoctorService doctorService;
    /**
     *  新增医生信息
     * */
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(@RequestBody @Valid DoctorSaveDto dto){
        DoctorEntity doctorEntity = new DoctorEntity();
        BeanUtils.copyProperties(dto, doctorEntity);
        doctorService.save(doctorEntity);
        return ResponseDTO.ok();
    }
}
