package air.admin.spring_boot.login.service;

import air.admin.spring_boot.login.vo.LoginReqVo;
import air.admin.spring_boot.login.vo.LoginResVo;
import air.admin.spring_boot.login.vo.RegisterReqVo;
import air.admin.spring_boot.login.vo.RegisterResVo;
import jakarta.validation.constraints.NotNull;

public interface UserService {
    /* 用户登录请求 */
    LoginResVo login(LoginReqVo login);

    RegisterResVo register(@NotNull RegisterReqVo register);
}
