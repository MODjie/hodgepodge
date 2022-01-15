package com.hodgepodge.order.exception;

import com.hodgepodge.exception.ErrorEntity;
import com.hodgepodge.exception.Return;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Title: GlobalErrorController
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月14日
 * @since 1.8
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class GlobalErrorController implements ErrorController {
    protected GlobalExceptionHandlerAdapter adapter;
    protected DefaultErrorAttributes errorAttributes = new DefaultErrorAttributes();

    public GlobalErrorController(GlobalExceptionHandlerAdapter adapter) {
        this.adapter = adapter;
    }

    @RequestMapping
    public ResponseEntity<ErrorEntity> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        WebRequest webRequest = new ServletWebRequest(request);
        String msg = getMessage(request);
        String requestUrl = Optional.ofNullable(request.getRequestURL()).map(StringBuffer::toString).orElse("");
        HttpHeaders headers = getHeaders(request);
        switch (status) {
            case NOT_FOUND:
                return adapter.handleException(request, new NoHandlerFoundException(request.getMethod(), requestUrl, headers));
            case UNAUTHORIZED:
                return adapter.handleException(request, Return.client().unauthorized(msg).build());
            case FORBIDDEN:
                return adapter.handleException(request, Return.client().accessDenied(msg).build());
            default:
                return adapter.handleException(request,
                        Optional
                                .ofNullable(this.errorAttributes.getError(webRequest))
                                .orElseGet(() -> Return.server().error(msg).build()));
        }
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<String> mediaTypeNotAcceptable(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        return ResponseEntity.status(status).build();
    }

    protected HttpHeaders getHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            Enumeration<String> headerValues = request.getHeaders(headerName);
            List<String> headerValueList = new ArrayList<>();
            while (headerValues.hasMoreElements()) {
                headerValueList.add(headerValues.nextElement());
            }
            headers.addAll(headerName, headerValueList);
        }
        return headers;
    }

    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    protected String getMessage(HttpServletRequest request) {
        return Optional.ofNullable((String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE)).orElse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
}
