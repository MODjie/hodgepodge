package com.hodgepodge.gateway.filter;

import com.hodgepodge.exception.Return;
import com.hodgepodge.gateway.config.CustomGatewayProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * <p>
 * Title: 令牌过滤器
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月25日
 * @since 1.8
 */
@Component
@EnableConfigurationProperties(CustomGatewayProperties.class)
public class TokenFilter implements GlobalFilter {

    private final String TOKEN = "token";
    private final String AUTHORIZATION = "authorization";

    @Resource
    private CustomGatewayProperties customGatewayProperties;

    @Resource
    private RestTemplate restTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        //验证是否传入token
        String token = request.getHeaders().getFirst(TOKEN);
        token = StringUtils.hasText(token) ? token : request.getHeaders().getFirst(AUTHORIZATION);
        if (!StringUtils.hasText(token)) {
            throw Return.client().unauthorized("未传入token").build();
        }
        //白名单直接放行
        if (!CollectionUtils.isEmpty(customGatewayProperties.getWhiteList())) {
            String path = request.getPath().value();
            if (customGatewayProperties.getWhiteList().contains(path)) {
                return chain.filter(exchange);
            }
        }
        //校验token是否正确
        if (!this.validateToken(token)) {
            throw Return.client().unauthorized("无效token或token已过期，请重新登录").build();
        }
        return chain.filter(exchange);
    }

    /**
     * <p>
     * Title: 校验Token
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param token
     * @return boolean
     * @author 刘小杰
     * @date 2022年01月25日
     * @since 1.8
     */
    private boolean validateToken(String token) {
        ResponseEntity<Boolean> response = restTemplate.getForEntity("http://ums-server/internal-auth/validateToken?token="+token, Boolean.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().booleanValue();
        }
        throw Return.server().msg("发送校验token请求异常").build();
    }
}
