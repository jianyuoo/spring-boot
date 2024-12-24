package air.admin.spring_boot.base.cases.service;

import air.admin.spring_boot.base.cases.dto.CasesQueryDto;
import air.admin.spring_boot.base.cases.dto.CasesSaveDto;
import air.admin.spring_boot.base.cases.dto.CasesupdateDto;
import air.admin.spring_boot.base.cases.entity.Cases;
import air.admin.spring_boot.base.cases.mapper.CaseMapper;
import air.admin.spring_boot.util.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CasesService extends ServiceImpl<CaseMapper, Cases> {

    @Autowired
    private CaseMapper caseMapper;

    //增、删、改、查
    public Result add( Cases dto) {
        return Result.success(save(dto));
    }

    public Result delete( CasesQueryDto casesQueryDto) {
        return Result.success(caseMapper.delete(casesQueryDto));
    }

    public Result update( CasesupdateDto dto) {
        caseMapper.update(dto);
        return Result.success();
    }

    public Result get( CasesQueryDto casesQueryDto) {
        return Result.success(caseMapper.getall(casesQueryDto));
    }
}
