package air.admin.spring_boot.base.nurse.controller;

import air.admin.spring_boot.base.nurse.dto.NurseQueryDto;
import air.admin.spring_boot.base.nurse.dto.NurseResultDto;
import air.admin.spring_boot.base.nurse.dto.NurseSaveDto;
import air.admin.spring_boot.base.nurse.entity.NurseEntity;
import air.admin.spring_boot.base.nurse.service.NurseService;
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

    @Operation(summary = "查询护士信息")
    @PostMapping("/select")
    public Result select (@Valid @RequestBody NurseQueryDto dto) {
        try {
            NurseResultDto nurse = nurseService.getAllNurse(dto); // 假设这里返回的是单个药品 DTO

            if (nurse != null) {
                return Result.success(nurse); // 返回查询到的药品对象
            } else {
                return Result.failure("未找到患者信息");
            }
        } catch (Exception e) {
            return Result.failure("获取患者信息出错: " + e.getMessage());
        }


    }
}
