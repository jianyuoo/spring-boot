package air.admin.spring_boot.module.registered.controller;

import air.admin.spring_boot.base.cases.dto.CasesQueryDto;
import air.admin.spring_boot.base.cases.entity.Cases;
import air.admin.spring_boot.base.cases.mapper.CaseMapper;
import air.admin.spring_boot.base.cases.service.CasesService;
import air.admin.spring_boot.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/register")
public class RegisterController {


    @Resource
    CasesService casesService;
    @Resource
    CaseMapper caseMapper;


    @PostMapping("/addRegistered")
    public Result addRegistered(@NotNull @RequestBody CasesQueryDto dto) {
        // 1.检验传入的患者id有没有已经创建的病例
        Cases existingCase = caseMapper.selectOne(new LambdaQueryWrapper<Cases>()
                .eq( Cases::getPatientid,dto.getPatientid())
                .eq(Cases::getDoctorid,dto.getDoctorid())
        );
        // 2.若已经创建过病例，则只更新时间
        if (existingCase != null) {
            // 更新病例逻辑
            existingCase.setUpdatetime(LocalDateTime.now());
        }
        // 3.若不存在病例记录则进行创建
        else {
            Cases newCase = new Cases();
            BeanUtils.copyProperties(dto, newCase);
            casesService.save(newCase);
        }
        return Result.success("操作成功");
    }


}

