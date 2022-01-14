package com.hodgepodge.gateway.exception;

import com.hodgepodge.exception.Code;
import com.hodgepodge.exception.ErrorEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.springframework.web.reactive.function.server.RequestPredicates.all;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * <p>
 * Title: GatewayGlobalExceptionHandler
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月13日
 * @since 1.8
 */
@Slf4j
public class GatewayGlobalExceptionHandler extends DefaultErrorWebExceptionHandler {

    protected final GatewayGlobalExceptionHandlerAdapter adapter;

    public GatewayGlobalExceptionHandler(GatewayGlobalExceptionHandlerAdapter adapter,ErrorAttributes errorAttributes, WebProperties.Resources resources, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resources, errorProperties, applicationContext);
        this.adapter = adapter;
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return route(all(), this::renderErrorResponse);
    }

    @Override
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        boolean includeStackTrace = isIncludeStackTrace(request, MediaType.ALL);
        Map<String, Object> errorMap = getErrorAttributes(request, (includeStackTrace) ? ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE) : ErrorAttributeOptions.defaults());
        Throwable ex = getError(request);
        int httpStatus = getHttpStatus(errorMap);
        Object errorBody = this.adapter.getErrorBody(request, httpStatus, ex);
        return ServerResponse
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorBody));
    }

}
