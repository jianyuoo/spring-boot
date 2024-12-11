package air.admin.spring_boot.module.doctor.controller;

import air.admin.spring_boot.module.doctor.dto.DoctorQueryDto;
import air.admin.spring_boot.module.doctor.dto.DoctorResultDto;
import air.admin.spring_boot.module.doctor.dto.DoctorSaveDto;
import air.admin.spring_boot.module.doctor.entity.DoctorEntity;
import air.admin.spring_boot.module.doctor.service.DoctorService;
import air.admin.spring_boot.util.common.ResponseDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/doctor")
public class DoctorController {

    @Resource
    private DoctorService doctorService;
    /**
     *  新增医生信息
     * */
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(@RequestBody DoctorSaveDto dto){
        DoctorEntity doctorEntity = new DoctorEntity();
        BeanUtils.copyProperties(dto, doctorEntity);
        doctorService.save(doctorEntity);
        return ResponseDTO.ok();
    }

    @PostMapping("/select")
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<List<DoctorResultDto>> page(@Valid @RequestBody DoctorQueryDto queryParam){
        IPage<DoctorResultDto> page = new Page<>(queryParam.getPageNum(),queryParam.getPageSize());
        page = doctorService.page(page, queryParam);
        return ResponseDTO.ok(page.getRecords());
    }
}
