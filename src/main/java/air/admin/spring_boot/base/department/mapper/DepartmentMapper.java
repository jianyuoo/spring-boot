package air.admin.spring_boot.base.department.mapper;

import air.admin.spring_boot.base.department.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

}
