package air.admin.spring_boot.base.nurse.Mapper;

import air.admin.spring_boot.base.nurse.entity.NurseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NurseMapper extends BaseMapper<NurseEntity> {
}
