package air.admin.spring_boot.module.people.service;


import air.admin.spring_boot.module.people.dto.PeopleQueryDto;
import air.admin.spring_boot.module.people.dto.PeopleResultDto;
import air.admin.spring_boot.module.people.entity.PeopleEntity;
import air.admin.spring_boot.module.people.mapper.PeopleMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class PeopleService extends ServiceImpl<PeopleMapper , PeopleEntity> {
    public IPage<PeopleResultDto> page(IPage<PeopleResultDto> page, PeopleQueryDto query) {
        return this.baseMapper.page(page, query);
    }
}
