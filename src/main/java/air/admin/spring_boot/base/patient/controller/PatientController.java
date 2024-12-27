package air.admin.spring_boot.base.patient.controller;

import air.admin.spring_boot.base.patient.dto.PatientQueryDto;
import air.admin.spring_boot.base.patient.dto.PatientResultDto;
import air.admin.spring_boot.base.patient.dto.PatientSaveDto;
import air.admin.spring_boot.base.patient.entity.PatientEntity;
import air.admin.spring_boot.base.patient.service.PatientService;
import air.admin.spring_boot.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Operation(summary = "患者新增")
    @PostMapping("/add")
    public Result add(@Valid @RequestBody PatientSaveDto dto){
        PatientEntity patient =new PatientEntity();
        return Result.success(patientService.set(dto,patient));
    }

    @Operation(summary = "药品查询")
    @PostMapping("/getpatient")
    public Result getAllPatient(@Valid @RequestBody PatientQueryDto dto) {
        try {
            PatientResultDto patient = patientService.getAllPatient(dto); // 假设这里返回的是单个药品 DTO

            if (patient != null) {
                return Result.success(patient); // 返回查询到的药品对象
            } else {
                return Result.failure("未找到患者信息");
            }
        } catch (Exception e) {
            return Result.failure("获取患者信息出错: " + e.getMessage());
        }

    }
    @Operation(summary = "患者删除")
    @PostMapping("/delete")
    public Result delate (@Valid @RequestBody PatientQueryDto dto){
        patientService.delete(dto);
        return Result.success();
    }
}
