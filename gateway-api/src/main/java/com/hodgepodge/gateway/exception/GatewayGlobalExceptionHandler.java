package com.hodgepodge.gateway.exception;

import com.hodgepodge.exception.Code;
import com.hodgepodge.exception.ErrorEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
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

    public GatewayGlobalExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
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
        Object errorBody = getErrorBody(request, httpStatus, ex);
        return ServerResponse
                .status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorBody));
    }

    public Object getErrorBody(ServerRequest request, int httpStatus, Throwable ex) {
        String msg = ex.getMessage();
        if (NullPointerException.class.isAssignableFrom(ex.getClass())) {
            msg = "空指针异常";
        }

        ErrorEntity serviceError =new ErrorEntity();
        switch (HttpStatus.valueOf(httpStatus)) {
            case INTERNAL_SERVER_ERROR:
                serviceError.setCode(Code.ERROR);
                serviceError.setMsg(msg);
                serviceError.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                break;
            case SERVICE_UNAVAILABLE:
                serviceError.setCode(Code.UNAVAILABLE);
                serviceError.setMsg(msg);
                serviceError.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE.value());
                break;
            default:
                serviceError.setCode(Code.ERROR);
                serviceError.setMsg(msg);
                serviceError.setStatusCode(httpStatus);
                break;
        }

        writeLog(ex, request, serviceError);

        return serviceError;
    }

    protected void writeLog(Throwable ex, ServerRequest request, Object msg) {
        HttpHeaders headersMap = request.headers().asHttpHeaders();
        StringBuffer headers = new StringBuffer("\r\n");
        headersMap.forEach((key, value) -> {
            headers.append("    ").append(key).append(": ").append(value).append("\r\n");
        });
        List<Object> args = new ArrayList<>();
        Collections.addAll(args, getUrl(request), request.methodName(), headers, msg);
        if (ex != null) {
            args.add(ex);
        }
        log.error("\r\n>>>请求网关异常 \r\n>Url: {}\r\n>Method: {}\r\n>Headers: {}\r\n>Msg: {}\r\n<<<", args.toArray());
    }

    private String getUrl(ServerRequest request) {
        String rawQuery = request.uri().getRawQuery();
        String query = StringUtils.hasText(rawQuery) ? "?" + rawQuery : "";
        return request.path() + query;
    }
}
