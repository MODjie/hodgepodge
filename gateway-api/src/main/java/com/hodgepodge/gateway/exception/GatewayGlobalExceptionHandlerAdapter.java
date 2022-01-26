package com.hodgepodge.gateway.exception;

import com.hodgepodge.exception.Code;
import com.hodgepodge.exception.ErrorEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Title: GatewayGlobalExceptionHandlerAdpter
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author <刘小杰>
 * @date 2022年01月14日
 * @since 1.8
 */
@Slf4j
public class GatewayGlobalExceptionHandlerAdapter {

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
