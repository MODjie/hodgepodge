package com.hodgepodge.order.config;

import com.hodgepodge.exception.config.ResourceServerExceptionConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>
 * Title: 资源服务器异常配置类
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author <刘小杰>
 * @date 2022年01月15日
 * @since 1.8
 */
@Configuration
@Import(ResourceServerExceptionConfig.class)
public class ExceptionConfig {


}
