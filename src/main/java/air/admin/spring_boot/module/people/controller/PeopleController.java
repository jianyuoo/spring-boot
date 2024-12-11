package air.admin.spring_boot.module.people.controller;

import air.admin.spring_boot.module.doctor.dto.DoctorQueryDto;
import air.admin.spring_boot.module.doctor.dto.DoctorResultDto;
import air.admin.spring_boot.module.doctor.dto.DoctorSaveDto;
import air.admin.spring_boot.module.doctor.entity.DoctorEntity;
import air.admin.spring_boot.module.people.dto.PeopleQueryDto;
import air.admin.spring_boot.module.people.dto.PeopleResultDto;
import air.admin.spring_boot.module.people.dto.PeopleSaveDto;
import air.admin.spring_boot.module.people.entity.PeopleEntity;
import air.admin.spring_boot.module.people.service.PeopleService;
import air.admin.spring_boot.util.common.ResponseDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v2/people")
public class PeopleController {

    @Resource
    private PeopleService peopleService;

    /**
     * 新增病人信息
     **/
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(@RequestBody PeopleSaveDto dto){
        PeopleEntity peopleEntity = new PeopleEntity();
        BeanUtils.copyProperties(dto, peopleEntity);
        peopleService.save(peopleEntity);
        return ResponseDTO.ok();
    }

    @PostMapping("/select")
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<List<PeopleResultDto>> page(@Valid @RequestBody PeopleQueryDto queryParam){
        IPage<PeopleResultDto> page = new Page<>(queryParam.getPageNum(),queryParam.getPageSize());
        page = peopleService.page(page, queryParam);
        return ResponseDTO.ok(page.getRecords());
    }
}
