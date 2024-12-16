package air.admin.spring_boot.base.drug.controller;

import air.admin.spring_boot.base.drug.dto.DrugQueryDto;
import air.admin.spring_boot.base.drug.dto.DrugSaveDto;
import air.admin.spring_boot.base.drug.entity.Drug;
import air.admin.spring_boot.base.drug.service.DrugService;
import air.admin.spring_boot.config.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "药品类", description = "药品使用相关")
@RestController
@RequestMapping("/drug")
public class DrugController {

    @Autowired
    private DrugService drugService;

    @Operation(summary = "药品新增")
    @PostMapping("/add")
    public Result add(@Valid @RequestBody DrugSaveDto dto){
        Drug drug = new Drug();
        BeanUtils.copyProperties(dto,drug);
        drugService.save(drug);
        return Result.success(drug);
    }

    @Operation(summary = "药品获取")
    @PostMapping("/getdrug")
    public Result getAllDrugs(@Valid @RequestBody DrugQueryDto dto) {
        return Result.success(drugService.getAllDrugs(dto));
    }
}
