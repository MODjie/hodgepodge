package com.hodgepodge.gateway.config;

import com.hodgepodge.gateway.exception.GatewayGlobalExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.stream.Collectors;

/**
 * <p>
 * Title: GatewayGlobalExceptionConfig
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月13日
 * @since 1.8
 */
@Configuration(proxyBeanMethods = false)
public class GatewayGlobalExceptionConfig {

    @Bean
    @Primary
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler gatewayGlobalExceptionHandler(
            ErrorAttributes errorAttributes,
            ResourceProperties resourceProperties,
            ServerProperties serverProperties,
            ApplicationContext applicationContext,
            ServerCodecConfigurer serverCodecConfigurer,
            ObjectProvider<ViewResolver> viewResolversProvider
            ) {
        GatewayGlobalExceptionHandler handler = new GatewayGlobalExceptionHandler(errorAttributes, resourceProperties, serverProperties.getError(), applicationContext);
        handler.setMessageWriters(serverCodecConfigurer.getWriters());
        handler.setViewResolvers(viewResolversProvider.orderedStream().collect(Collectors.toList()));
        handler.setMessageReaders(serverCodecConfigurer.getReaders());
        return handler;
    }
}
