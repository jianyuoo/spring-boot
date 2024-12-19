package air.admin.spring_boot.base.nurse.service;

import air.admin.spring_boot.base.nurse.Mapper.NurseMapper;
import air.admin.spring_boot.base.nurse.entity.NurseEntity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NurseService extends ServiceImpl<NurseMapper, NurseEntity> {
    @Autowired
    private NurseMapper nurseMapper;

}
