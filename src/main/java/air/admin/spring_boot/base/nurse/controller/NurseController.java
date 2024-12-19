package air.admin.spring_boot.base.nurse.controller;

import air.admin.spring_boot.base.nurse.dto.NurseSaveDto;
import air.admin.spring_boot.base.nurse.entity.NurseEntity;
import air.admin.spring_boot.base.nurse.service.NurseService;
import air.admin.spring_boot.base.patient.entity.PatientEntity;
import air.admin.spring_boot.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nurse")
public class NurseController {
    @Autowired
    private NurseService nurseService;
    @Operation(summary = "添加护士")
    @PostMapping("/add")
    public Result add(@Valid @RequestBody NurseSaveDto dto){
        NurseEntity nurse =new NurseEntity();
        BeanUtils.copyProperties(dto,nurse);
        nurseService.save(nurse);
        return Result.success(nurse);
    }
}
