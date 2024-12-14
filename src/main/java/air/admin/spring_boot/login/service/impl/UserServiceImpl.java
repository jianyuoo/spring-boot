package air.admin.spring_boot.login.service.impl;

import air.admin.spring_boot.login.vo.RegisterReqVo;
import air.admin.spring_boot.login.vo.RegisterResVo;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import air.admin.spring_boot.login.mapper.UserMapper;
import air.admin.spring_boot.login.entity.User;
import air.admin.spring_boot.login.mapper.UserConvert;
import air.admin.spring_boot.login.vo.LoginReqVo;
import air.admin.spring_boot.login.vo.LoginResVo;
import air.admin.spring_boot.login.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.Date;
import java.time.LocalDateTime;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private UserConvert userConvert;

    /* 用户登录请求 */
    @Override
    public LoginResVo login(LoginReqVo login) {
        User user = userMapper.selectOne(
                new LambdaQueryWrapper<User>()
                        .select(User::getUsername,User::getPassword,User::getCreatetime,User::getUsername,User::getUpdatetime)
                        .eq(User::getUsername, login.getUsername())
        );

        /**
         * assert(条件语句)，如果条件语句为真，继续往下执行，如果为假就会报错
         * */
        Assert.notNull(user, "用户不存在");
        Assert.isTrue(BCrypt.checkpw(login.getPassword(), user.getPassword()),"密码错误");

        // 校验指定账号是否已被封禁，如果被封禁则抛出异常 `DisableServiceException`
        StpUtil.checkDisable(user.getId());
        // 通过校验后，再进行登录
        StpUtil.login(user.getId());

        // entity数据转为vo响应的数据
        LoginResVo loginRes = userConvert.toLoginRes(user);
        loginRes.setToken(StpUtil.getTokenValue());

        return loginRes;
    }

    @Override
    public RegisterResVo register(@NotNull RegisterReqVo register) {

        // 检查用户名是否已存在
        User existingUser = userMapper.selectOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getUsername, register.getUsername())
        );

        Assert.isNull(existingUser, "用户名已存在"); // 如果用户名已存在，则抛出异常

        // 创建新用户对象
        User newUser = new User();
        newUser.setUsername(register.getUsername());
        // 使用 bcrypt 加密密码
        String encryptedPassword = BCrypt.hashpw(register.getPassword());
        newUser.setPassword(encryptedPassword);

        // 其他字段可以根据需要进行初始化
        newUser.setCreatetime(LocalDateTime.now());
        newUser.setUpdatetime(LocalDateTime.now());

        // 插入新用户数据到数据库
        userMapper.insert(newUser);

        // 可选：响应对象
        RegisterResVo registerRes = new RegisterResVo();
        registerRes.setUsername(newUser.getUsername());
        registerRes.setCreatetime(newUser.getCreatetime());
        registerRes.setUpdatetime(newUser.getUpdatetime());
        // entity数据转为vo响应的数据
        RegisterResVo loginRes = userConvert.toRegisterResVo(newUser);

        return registerRes;
    }

}
