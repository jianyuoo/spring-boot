package air.admin.spring_boot.module.login.service;

import air.admin.spring_boot.base.constant.RedisConstant;
import air.admin.spring_boot.module.login.entity.SystemUserEntity;
import air.admin.spring_boot.module.login.mapper.LoginMapper;
import air.admin.spring_boot.module.login.vo.CaptchaVo;
import air.admin.spring_boot.module.login.vo.LoginVo;
import air.admin.spring_boot.module.login.vo.SystemUserInfoVo;
import air.admin.spring_boot.util.LeaseException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import air.admin.spring_boot.util.JwtUtil;
import air.admin.spring_boot.base.constant.ResultCodeEnum;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginService extends ServiceImpl<LoginMapper,SystemUserEntity> {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public CaptchaVo getCaptcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);

        String code = specCaptcha.text().toLowerCase();
        String key = RedisConstant.ADMIN_LOGIN_PREFIX + UUID.randomUUID();
        String image = specCaptcha.toBase64();
        redisTemplate.opsForValue().set(key, code, RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);

        return new CaptchaVo(image, key);
    }

    public String login(LoginVo loginVo) throws LeaseException {
        //1.判断是否输入了验证码
        if (!StringUtils.hasText(loginVo.getCaptchaCode())) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);
        }

        //2.校验验证码
        String code = redisTemplate.opsForValue().get(loginVo.getCaptchaKey());
        if (code == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
        }

        if (!code.equals(loginVo.getCaptchaCode().toLowerCase())) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }

        //3.校验用户是否存在
        SystemUserEntity systemUser = this.baseMapper.selectOneByUsername(loginVo.getUsername());

        if (systemUser == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        }

        //4.校验用户密码
        if (!new BCryptPasswordEncoder().matches(loginVo.getPassword(), systemUser.getPassword())) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);
        }

        //5.创建并返回TOKEN
        return JwtUtil.createToken(systemUser.getId(), systemUser.getUsername());
    }

    public SystemUserInfoVo getLoginUserInfo(Long userId) {
        SystemUserEntity systemUser = this.baseMapper.selectById(userId);
        SystemUserInfoVo systemUserInfoVo = new SystemUserInfoVo();
        systemUserInfoVo.setName(systemUser.getName());
        systemUserInfoVo.setAvatarUrl(systemUser.getAvatarUrl());
        return systemUserInfoVo;
    }
}
