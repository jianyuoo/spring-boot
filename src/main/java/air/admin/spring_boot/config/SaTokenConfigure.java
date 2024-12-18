package air.admin.spring_boot.config;

import air.admin.spring_boot.config.vo.Result;
import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson2.JSON;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static air.admin.spring_boot.util.enums.StatusCodeEnum.*;


/**
  * [Sa-Token 权限认证] 配置类 => https://sa-token.cc/doc.html#/up/global-filter
 */
        
        
@Configuration
public class SaTokenConfigure {

    // 放行路径
    private final String[] EXCLUDE_PATH_PATTERNS = {
            "/swagger-resources",
                    "/webjars/**",
                    "/v2/api-docs",
                    "/doc.html",
                    "/favicon.ico",
                    "/login",
                    "/oauth/*",
                    "drug/*"
    };

    private final long timeout = 600;

        
    /**
      * 注册 [Sa-Token全局过滤器]
      */
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                // 拦截路径
                .addInclude("/**")
                // 放行路由
                .addExclude(EXCLUDE_PATH_PATTERNS)
                // 前置函数：在每次认证函数之前执行（BeforeAuth 不受 includeList 与 excludeList 的限制，所有请求都会进入）
                .setBeforeAuth(r -> {
                    // ---------- 设置一些安全响应头 ----------
                    SaHolder.getResponse()
                            // 服务器名称
                            .setServer("sa-server")
                            // 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以
                            .setHeader("X-Frame-Options", "SAMEORIGIN")
                            // 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
                            .setHeader("X-XSS-Protection", "1; mode=block")
                            // 禁用浏览器内容嗅探
                            .setHeader("X-Content-Type-Options", "nosniff")
                            /* ---------------------------------------------------------- */
                            // 允许指定域访问跨域资源
                            .setHeader("Access-Control-Allow-Origin", "*")
                            // 允许所有请求方式
                            .setHeader("Access-Control-Allow-Methods", "*")
                            // 有效时间
                            .setHeader("Access-Control-Max-Age", "3600")
                            // 允许的header参数
                            .setHeader("Access-Control-Allow-Headers", "*");
                    ;
                })
                // 认证函数: 每次请求执行
                .setAuth(obj -> {
                    System.out.println("请求拦截 => ");
                    // 检查是否登录
                    SaRouter.match("/admin/**").check(r -> StpUtil.checkLogin());
                    // 刷新token有效期
                    if (StpUtil.getTokenTimeout() < timeout) {
                        StpUtil.renewTimeout(1800);
                    }
                    // 输出 API 请求日志，方便调试代码
                    SaManager.getLog().debug("----- 请求path={}  提交token={}", SaHolder.getRequest().getRequestPath(), StpUtil.getTokenValue());
                })
                //  异常处理函数：每次认证函数发生异常时执行此函数
                .setError(e -> {
                    // 设置响应头
                    SaHolder.getResponse().setHeader("Content-Type", "application/json;charset=UTF-8");
                    if (e instanceof NotLoginException) {
                        return JSON.toJSONString(Result.fail(UNAUTHORIZED.getCode(), UNAUTHORIZED.getMsg()));
                    }
                    // TODO 服务器后端在这里无法捕获异常，仅仅将异常信息传给了前端
                    e.printStackTrace();
                    return SaResult.error(e.getMessage());
                });
    }

}

