package air.admin.spring_boot.util.Security.config;


import air.admin.spring_boot.util.Security.service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;

@Configuration
public class Secuityconfig {

    private String[] URL_WHITELIST = {
            "/v1/**",
            "/drug/**",
            "/patient/**",
            "/nurse/**",
            "/doctor/**"
    };
    private final CustomerUserDetailsService customUserDetailsService;

    public Secuityconfig(CustomerUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }


    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtTokenProvider(), customUserDetailsService());
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider();
    }

    @Bean
    public CustomerUserDetailsService customUserDetailsService() {
        return new CustomerUserDetailsService();
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new ProviderManager(new DaoAuthenticationProvider() {{
            setUserDetailsService(customUserDetailsService);
            setPasswordEncoder(bCryptPasswordEncoder());
        }});
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // 禁用 CSRF 保护
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(URL_WHITELIST).permitAll() // 允许访问无需认证的路径
                        .anyRequest().authenticated())// 其他请求需要身份验证
                .logout(Customizer.withDefaults()) // 使用默认的注销设置
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 不使用会话，基于 token 的认证
                .userDetailsService(customUserDetailsService) // 设置自定义用户详细信息服务
//                .exceptionHandling(exceptionHandle -> exceptionHandle
//                        .authenticationEntryPoint(loginAuthenticationEntryPoint) // 设置未认证处理器
//                        .accessDeniedHandler(authAccessDeniedHandler) // 设置访问被拒绝处理器
//                )
                .addFilterAfter(jwtAuthenticationFilter(), ExceptionTranslationFilter.class) // 添加自定义的 JWT 认证过滤器
                .httpBasic(Customizer.withDefaults()); // 启用基本身份验证
        return http.build();
    }
}
