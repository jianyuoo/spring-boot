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
        List<Department> department = departmentMapper.selectList(new LambdaQueryWrapper<Department>());
        // 2.返回部门名称列表
        List<String> departmentNames = department.stream()
                .map(Department::getDepartment) // 假设Doctor类有getName()方法
                .toList(); // 在Java 11及以上可以使用toList()
        return Result.success(departmentNames);
    }


    @GetMapping("/selectDoctor")
    public Result selectDoctor(@Param("departmentName") String departmentName) {
        // 1. 根据部门名称查部门ID
        Department department = departmentMapper.selectOne(new LambdaQueryWrapper<Department>()
                .like(Department::getDepartment, departmentName)
        );

        // 确保查找到的部门不为空
        if (department == null) {
            return Result.failure("未找到相关部门");
        }

        // 获取部门ID
        Long departmentId = department.getDepartmentid(); // 假设Department类有getId()方法

        // 2. 根据部门ID查找医生
        List<Doctor> doctors = doctorMapper.selectList(
                new LambdaQueryWrapper<Doctor>()
                        .eq(Doctor::getDepartmentid, departmentId) // 使用获取到的部门ID作为查询条件
        );

        // 如果只想要医生的名字，可以提取名字列表
        List<String> doctorNames = doctors.stream()
                .map(Doctor::getName) // 假设Doctor类有getName()方法
                .toList(); // 在Java 11及以上可以使用toList()

        // 3. 返回成功结果
        return Result.success(doctorNames);

    }
}
