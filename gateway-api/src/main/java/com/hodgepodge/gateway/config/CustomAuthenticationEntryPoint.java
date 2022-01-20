package com.hodgepodge.gateway.config;

import com.hodgepodge.exception.Return;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.server.resource.BearerTokenError;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * Title: 自定义认证入口
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月20日
 * @since 1.8
 */
@Component
public class CustomAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException authException) {
        if (authException instanceof AuthenticationCredentialsNotFoundException){
            throw Return.client("请先登录").unauthorized().build();
        }
        throw Return.server().msg(authException.getMessage()).build();
    }

}
