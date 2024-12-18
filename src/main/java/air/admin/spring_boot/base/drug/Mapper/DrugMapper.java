package air.admin.spring_boot.base.drug.Mapper;

import air.admin.spring_boot.base.drug.dto.DrugQueryDto;
import air.admin.spring_boot.base.drug.dto.DrugResultDto;
import air.admin.spring_boot.base.drug.dto.DrugUpdataDto;
import air.admin.spring_boot.base.drug.entity.Drug;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DrugMapper extends BaseMapper<Drug> {

    DrugResultDto selec(DrugQueryDto query);
    void delete(DrugQueryDto query);

}
