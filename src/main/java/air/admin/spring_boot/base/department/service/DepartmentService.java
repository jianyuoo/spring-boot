package air.admin.spring_boot.base.department.service;

import air.admin.spring_boot.base.department.entity.Department;
import air.admin.spring_boot.base.department.mapper.DepartmentMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends ServiceImpl<DepartmentMapper, Department> {

}
