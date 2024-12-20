package air.admin.spring_boot.base.examine.mapper;

import air.admin.spring_boot.base.examine.entity.Examine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExamineMapper extends BaseMapper<Examine> {
}
