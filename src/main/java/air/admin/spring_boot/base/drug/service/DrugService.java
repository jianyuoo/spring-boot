package air.admin.spring_boot.base.drug.service;

import air.admin.spring_boot.base.drug.entity.Drug;
import air.admin.spring_boot.base.drug.Mapper.DrugMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DrugService {

    @Autowired
    private DrugMapper drugMapper; // 注入DrugMapper

    // 创建药品
    @Transactional
    public Drug createDrug(Drug drug) {
        // 数据验证
        validateDrug(drug);

        // 插入药品记录
        drugMapper.insert(drug);
        return drug;
    }

    // 获取所有药品
    public List<Drug> getAllDrugs() {
        return drugMapper.selectList(null);
    }

    // 根据Id获取药品
    public Drug getDrugById(int drugid) {
        return drugMapper.selectById(drugid);
    }

    // 更新药品信息
    @Transactional
    public Drug updateDrug(Drug drug) {
        // 数据验证
        validateDrug(drug);

        // 更新药品记录
        drugMapper.updateById(drug);
        return drug;
    }

    // 删除药品
    @Transactional
    public void deleteDrug(int drugid) {
        drugMapper.deleteById(drugid);
    }

    // 数据验证
    private void validateDrug(Drug drug) {
        if (drug.getDrugname() == 0 || drug.getDrugusedisease() == null || drug.getDrugusedisease().isEmpty()) {
            throw new IllegalArgumentException("Drug name and used disease cannot be null or empty");
        }
    }
}
