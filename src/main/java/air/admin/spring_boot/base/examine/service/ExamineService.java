package air.admin.spring_boot.base.examine.service;

import air.admin.spring_boot.base.examine.entity.Examine;
import air.admin.spring_boot.base.examine.mapper.ExamineMapper;
import air.admin.spring_boot.util.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamineService extends ServiceImpl<ExamineMapper, Examine> {

    @Autowired
    private ExamineMapper examineMapper;

    /**
     * 创建表单
     * */
    public Result CreateExamine(Examine examine) {
        save(examine);
        return Result.success();
    }

    /**
     * 修改表单数据
     * */
    public Result UpdateExamine(Examine examine) {
        updateById(examine);
        return Result.success();
    }

    /**
     * 删除表单数据
     * */
    public Result DeleteExamine(Examine examine) {
        examineMapper.deleteById(examine);
        return Result.success();
    }

    /**
     * 查询表单数据
     * */
    public Result GetExamineById(@NotNull Examine examine) {
        return Result.success(examineMapper.selectById(examine.getExamineid()));
    }
}
