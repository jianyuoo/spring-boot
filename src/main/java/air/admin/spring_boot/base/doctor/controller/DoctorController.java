package air.admin.spring_boot.base.doctor.controller;


import air.admin.spring_boot.base.doctor.dto.DoctorQueryDto;
import air.admin.spring_boot.base.doctor.dto.DoctorSaveDto;
import air.admin.spring_boot.base.doctor.entity.Doctor;
import air.admin.spring_boot.base.doctor.service.DoctorService;
import air.admin.spring_boot.util.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "医生类", description = "医生信息相关")
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    //医生信息增加
    @PostMapping("/add")
    public Result add(@RequestBody DoctorSaveDto dto) {
        Doctor doctor = new Doctor();
        return Result.success(doctorService.set(dto,doctor));
    }

    //医生信息查询
    @GetMapping("/select")
    public Result findAll(@RequestBody DoctorQueryDto dto ) {
        return Result.success(doctorService.select(dto));
    }
}
