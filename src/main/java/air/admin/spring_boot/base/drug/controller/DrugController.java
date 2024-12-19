package air.admin.spring_boot.base.drug.controller;

import air.admin.spring_boot.base.drug.dto.DrugQueryDto;
import air.admin.spring_boot.base.drug.dto.DrugResultDto;
import air.admin.spring_boot.base.drug.dto.DrugSaveDto;
import air.admin.spring_boot.base.drug.dto.DrugUpdataDto;
import air.admin.spring_boot.base.drug.entity.Drug;
import air.admin.spring_boot.base.drug.service.DrugService;
import air.admin.spring_boot.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "药品类", description = "药品使用相关")
@RestController
@RequestMapping("/drug")
public class DrugController {

    @Autowired
    private DrugService drugService;

//    @HasPermission("ADMIN_PERMISSION")
    @Operation(summary = "药品新增")
    @PostMapping("/add")
    public Result add(@Valid @RequestBody DrugSaveDto dto){
        String url = drugService.setImage(dto);
        dto.setDrugurl(url);
        Drug drug = new Drug();
        BeanUtils.copyProperties(dto,drug);
        drugService.save(drug);
        return Result.success(drug);
    }


    @Operation(summary = "药品查询")
    @PostMapping("/getdrug")
    public Result getAllDrugs(@Valid @RequestBody DrugQueryDto dto) {
        try {
            // 调用服务层获取药品信息
            DrugResultDto drug = drugService.getAllDrugs(dto); // 假设这里返回的是单个药品 DTO

            if (drug != null) {
                return Result.success(drug); // 返回查询到的药品对象
            } else {
                return Result.failure("未找到对应药品");
            }
        } catch (Exception e) {
            // 记录异常并返回错误信息
//            Logger.error("获取药品信息出错", e); // 记录完整的异常堆栈
            return Result.failure("获取药品信息出错: " + e.getMessage());
        }

    }

    @Operation(summary = "药品更新")
    @PostMapping("/update")
    public Result updata(@Valid @RequestBody DrugUpdataDto dto){
        Drug drug = new Drug();
        BeanUtils.copyProperties(dto, drug);
        try {
            boolean success = drugService.updateById(drug);
            if (success) {
                return Result.success("药品更新成功");
            } else {
                return Result.failure("药品更新失败，未找到对应药品");
            }
        } catch (Exception e) {
            return Result.failure("药品更新出错: " + e.getMessage());
        }
    }

    @Operation(summary = "药品删除")
    @PostMapping("/delec")
    public Result delec(){
        return Result.success();
    }

    @Operation(summary = "药品删除")
    @PostMapping("/delete")
    public Result delate (@Valid @RequestBody DrugQueryDto dto){
        drugService.delete(dto);
        return Result.success();
    }
}
