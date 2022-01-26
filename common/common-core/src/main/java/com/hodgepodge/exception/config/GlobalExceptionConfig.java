package com.hodgepodge.exception.config;

import com.hodgepodge.exception.GlobalErrorController;
import com.hodgepodge.exception.GlobalExceptionHandler;
import com.hodgepodge.exception.GlobalExceptionHandlerAdapter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Title: 全局异常配置类
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author <刘小杰>
 * @date 2022年01月15日
 * @since 1.8
 */
@ConditionalOnWebApplication
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
@ConditionalOnProperty(value = "resource-server.use-restful-error-handler", matchIfMissing = true)
public class GlobalExceptionConfig {

    @Bean
    @ConditionalOnMissingBean
    public GlobalExceptionHandler globalExceptionHandler(GlobalExceptionHandlerAdapter adapter) {
        return new GlobalExceptionHandler(adapter);
    }

    @Bean
    @ConditionalOnMissingBean
    public GlobalExceptionHandlerAdapter globalExceptionHandlerAdapter() {
        return new GlobalExceptionHandlerAdapter();
    }

    @Bean
    @ConditionalOnMissingBean(value = GlobalErrorController.class, search = SearchStrategy.CURRENT)
    public GlobalErrorController globalErrorController(GlobalExceptionHandlerAdapter adapter) {
        return new GlobalErrorController(adapter);
    }

}
