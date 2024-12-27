package air.admin.spring_boot.base.department.controller;

import air.admin.spring_boot.base.department.entity.Department;
import air.admin.spring_boot.base.department.mapper.DepartmentMapper;
import air.admin.spring_boot.base.doctor.entity.Doctor;
import air.admin.spring_boot.base.doctor.mapper.DoctorMapper;
import air.admin.spring_boot.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private DoctorMapper doctorMapper;

    @GetMapping("/selectDepartment")
    public Result selectDepartment() {
        // 1.查询单表中所有的科室
        List<Department> department = departmentMapper.selectList(new LambdaQueryWrapper<>());
        // 2.返回部门名称列表
        List<String> departmentNames = department.stream()
                .map(Department::getDepartment)
                .toList();
        return Result.success(departmentNames);
    }
    @GetMapping("/selectDoctor")
    public Result selectDoctor(@Param("departmentName") String departmentName) {
        return Result.success(doctorMapper.selectDoctor(departmentName));

    }
}
