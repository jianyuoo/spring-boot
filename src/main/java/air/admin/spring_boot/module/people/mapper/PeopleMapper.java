package air.admin.spring_boot.module.people.mapper;

import air.admin.spring_boot.module.people.dto.PeopleQueryDto;
import air.admin.spring_boot.module.people.dto.PeopleResultDto;
import air.admin.spring_boot.module.people.entity.PeopleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PeopleMapper extends BaseMapper<PeopleEntity> {
    IPage<PeopleResultDto> page(IPage<PeopleResultDto> page, @Param("param") PeopleQueryDto query);
}
