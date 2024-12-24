package air.admin.spring_boot.base.cases.mapper;

import air.admin.spring_boot.base.cases.dto.CasesQueryDto;
import air.admin.spring_boot.base.cases.dto.CasesResultDto;
import air.admin.spring_boot.base.cases.dto.CasesSaveDto;
import air.admin.spring_boot.base.cases.dto.CasesupdateDto;
import air.admin.spring_boot.base.cases.entity.Cases;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CaseMapper extends BaseMapper<Cases> {
    Integer delete(CasesQueryDto casesQueryDto);

    void update(CasesupdateDto dto);

    CasesResultDto getall(CasesQueryDto casesQueryDto);

}
