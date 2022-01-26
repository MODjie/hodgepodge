package com.hodgepodge.ums.auth.config;

import com.hodgepodge.ums.auth.filter.DefaultAuthenticationFilter;
import com.hodgepodge.ums.auth.service.CustomUserDetailsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * <p>
 * Title: DefaultSecurityConfig
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月18日
 * @since 1.8
 */
@EnableWebSecurity
public class DefaultSecurityConfig {

    private final String DEFAULT_FILTER_PROCESS_URL = "/auth/login";

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                                authorizeRequests
                                        .antMatchers("/auth/**").permitAll()
                                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .exceptionHandling(exception->exception.authenticationEntryPoint(this.defaultAuthenticationEntryPoint()))
                .addFilter(this.defaultAuthenticationFilter());
        return http.build();
    }

    /**
     * <p>
     * Title: 往容器注入默认的认证服务
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return org.springframework.security.core.userdetails.UserDetailsService
     * @author 刘小杰
     * @date 2022年01月26日
     * @since 1.8
     */
    @Bean
    UserDetailsService users() {
        return new CustomUserDetailsService();
    }

    /**
     * <p>
     * Title: 往容器注入默认的密码编码器
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return org.springframework.security.crypto.password.PasswordEncoder
     * @author 刘小杰
     * @date 2022年01月26日
     * @since 1.8
     */
    @ConditionalOnMissingBean
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new Md5Encoder();
    }

    /**
     * <p>
     * Title: 往容器注入默认的认证入口
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return com.hodgepodge.ums.auth.config.DefaultAuthenticationEntryPoint
     * @author 刘小杰
     * @date 2022年01月26日
     * @since 1.8
     */
    @Bean
    public DefaultAuthenticationEntryPoint defaultAuthenticationEntryPoint(){
        return new DefaultAuthenticationEntryPoint();
    }

    /**
     * <p>
     * Title: 往容器注入默认的认证过滤器
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return com.hodgepodge.ums.auth.filter.DefaultAuthenticationFilter
     * @author 刘小杰
     * @date 2022年01月26日
     * @since 1.8
     */
    @Bean
    public DefaultAuthenticationFilter defaultAuthenticationFilter(){
        return new DefaultAuthenticationFilter(DEFAULT_FILTER_PROCESS_URL);
    }
}
