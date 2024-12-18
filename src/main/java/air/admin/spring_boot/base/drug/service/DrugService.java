package air.admin.spring_boot.base.drug.service;

import air.admin.spring_boot.base.drug.dto.DrugQueryDto;
import air.admin.spring_boot.base.drug.dto.DrugResultDto;
import air.admin.spring_boot.base.drug.Mapper.DrugMapper;
import air.admin.spring_boot.base.drug.dto.DrugSaveDto;
import air.admin.spring_boot.base.drug.entity.Drug;
import air.admin.spring_boot.util.MinioUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.validation.Valid;
import org.checkerframework.checker.units.qual.Volume;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class DrugService extends ServiceImpl<DrugMapper, Drug> {

    @Autowired
    private DrugMapper drugMapper; // 注入DrugMapper

    @Autowired
    private MinioUtils minioUtils;

    @Value("${minio.bucketName}")
    private String bucketName;

    // 获取所有药品
    public DrugResultDto getAllDrugs(@Valid DrugQueryDto dto) {
        return drugMapper.selec(dto);
    }
    public void delete(@Valid DrugQueryDto dto) {
        drugMapper.delete(dto);
    }

    //上传照片
    public String setImage(@NotNull @Valid DrugSaveDto dto){
        minioUtils.uploadImage(bucketName,dto.getDrugurl(),dto.getDrugname(),dto.getDrugtype());
        String name = dto.getDrugname() +"-"+ dto.getDrugtype() + ".jpg";
        return minioUtils.getPresignedObjectUrl(bucketName,name);
    }
}
