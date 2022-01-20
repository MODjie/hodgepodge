package com.hodgepodge.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * <p>
 * Title: 资源服务器配置
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月18日
 * @since 1.8
 */
@EnableWebFluxSecurity
public class ResourceServerConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    private AuthorizationManager authorizationManager;

    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public ResourceServerConfig(AuthorizationManager authorizationManager,CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.authorizationManager = authorizationManager;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Bean
    SecurityWebFilterChain resourceServerSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange()
                        .access(authorizationManager)

                )
                .exceptionHandling(exceptionHandlingSpec -> exceptionHandlingSpec.authenticationEntryPoint(customAuthenticationEntryPoint))
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return ReactiveJwtDecoders.fromIssuerLocation(issuerUri);
    }
}
