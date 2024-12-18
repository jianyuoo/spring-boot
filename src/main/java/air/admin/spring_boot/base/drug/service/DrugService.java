package air.admin.spring_boot.base.drug.service;

import air.admin.spring_boot.base.drug.dto.DrugQueryDto;
import air.admin.spring_boot.base.drug.dto.DrugResultDto;
import air.admin.spring_boot.base.drug.Mapper.DrugMapper;
import air.admin.spring_boot.base.drug.entity.Drug;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class DrugService extends ServiceImpl<DrugMapper, Drug> {

    @Autowired
    private DrugMapper drugMapper; // 注入DrugMapper

    // 获取所有药品
    public DrugResultDto getAllDrugs(@Valid DrugQueryDto dto) {
        return drugMapper.selec(dto);
    }
    public void delete(@Valid DrugQueryDto dto) {
        drugMapper.delete(dto);
    }
}
