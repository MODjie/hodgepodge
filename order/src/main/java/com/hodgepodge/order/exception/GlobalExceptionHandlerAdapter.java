package com.hodgepodge.order.exception;

import com.hodgepodge.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import javax.validation.Path;
import javax.validation.constraints.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * <p>
 * Title: Controller全局异常处理器适配器
 * </p>
 * <p>
 * Description:继承该类并且注册到Spring上下文中， 并通过重写对应的异常处理方法可以修改异常处理过程， 默认被GlobalExceptionHandler引用
 * </p>
 *
 * @author <刘小杰>
 * @date 2022年01月15日
 * @since 1.8
 */
@Slf4j
public class GlobalExceptionHandlerAdapter {

    @Value("${spring.application.name:application}")
    protected String serviceName;

    public GlobalExceptionHandlerAdapter() {
    }

    /**
     * <p>
     * Title: 处理并创建异常响应
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param request 请求
     * @param ex      异常
     * @return org.springframework.http.ResponseEntity<java.lang.Error>
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    public ResponseEntity<ErrorEntity> handleException(HttpServletRequest request, Throwable ex) {
        ResponseEntity<ErrorEntity> responseEntity = handleException(ex);
        printLog(ex, request, responseEntity.getStatusCode().value(), responseEntity.getBody());
        return responseEntity;
    }

    /**
     * <p>
     * Title: 处理并创建异常响应
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param ex 异常
     * @return org.springframework.http.ResponseEntity<java.lang.Error>
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    public ResponseEntity<ErrorEntity> handleException(Throwable ex) {
        return buildResponse(GlobalExceptionHelper.unwrapException(ex));
    }

    /**
     * <p>
     * Title: 创建异常响应
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param ex 异常类
     * @return org.springframework.http.ResponseEntity<java.lang.Error>
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    public ResponseEntity<ErrorEntity> buildResponse(Throwable ex) {
        Class<? extends Throwable> exCls = ex.getClass();
        ErrorEntity serviceError;
        if (ReturnException.class.isAssignableFrom(ex.getClass())) {
            serviceError = ((ReturnException) ex).getError();
        } else if (ExceptionTypeMatcher.matchesException(exCls, ExceptionTypeMatcher.BAD_REQUEST_EXCEPTION_CLASS)) {
            serviceError = buildBadRequest(ex);
        } else if (ExceptionTypeMatcher.matchesException(exCls, ExceptionTypeMatcher.UNAUTHORIZED_EXCEPTION_CLASS)) {
            serviceError = buildUnauthorized(ex);
        } else if (ExceptionTypeMatcher.matchesException(exCls, ExceptionTypeMatcher.FORBIDDEN_EXCEPTION_CLASS)) {
            serviceError = buildForbidden();
        } else if (ExceptionTypeMatcher.matchesException(exCls, ExceptionTypeMatcher.NOT_FOUND_EXCEPTION_CLASS)) {
            serviceError = buildNotFound();
        } else if (ExceptionTypeMatcher.matchesException(exCls, ExceptionTypeMatcher.METHOD_NOT_ALLOWED_EXCEPTION_CLASS)) {
            serviceError = buildMethodNotAllowed();
        } else if (ExceptionTypeMatcher.matchesException(exCls, ExceptionTypeMatcher.UNSUPPORTED_MEDIA_TYPE_EXCEPTION_CLASS)) {
            serviceError = buildMediaTypeNotSupported();
        } else if (ExceptionTypeMatcher.matchesException(exCls, ExceptionTypeMatcher.NOT_IMPLEMENTED_EXCEPTION_CLASS)) {
            serviceError = buildNotImplemented();
        } else if (ExceptionTypeMatcher.matchesException(exCls, ExceptionTypeMatcher.BAD_GATEWAY_EXCEPTION_CLASS)) {
            serviceError = buildBadGateway();
        } else if (ExceptionTypeMatcher.matchesException(exCls, ExceptionTypeMatcher.SERVICE_UNAVAILABLE_EXCEPTION_CLASS)) {
            serviceError = buildServiceUnavailable();
        } else if (ExceptionTypeMatcher.matchesException(exCls, ExceptionTypeMatcher.GATEWAY_TIMEOUT_EXCEPTION_CLASS)) {
            serviceError = buildGatewayTimeout();
        } else if (ExceptionTypeMatcher.matchesException(exCls, ClientApiException.class)) {
            serviceError = ((ApiException) ex).getResponseError();
        } else if (ExceptionTypeMatcher.matchesException(exCls, ServerApiException.class)) {
            serviceError = ((ApiException) ex).getApiError();
        } else {
            serviceError = buildInternalServer(ex);
        }
        // 设置空白错误信息
        if (!StringUtils.hasText(serviceError.getMsg())) {
            serviceError.setMsg(getMessage(ex));
        }
        if (serviceError.getStatusCode() <= 0) {
            serviceError.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return ResponseEntity
                .status(Optional.ofNullable(HttpStatus.resolve(serviceError.getStatusCode())).orElse(HttpStatus.INTERNAL_SERVER_ERROR))
                .header("Cache-Control", "no-store")
                .header("Pragma", "no-cache")
                .contentType(MediaType.APPLICATION_JSON)
                .body(serviceError);

    }


    /**
     * <p>
     * Title: 创建客户端错误(400)异常响应对象
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param ex
     * @return com.hodgepodge.exception.ErrorEntity
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected ErrorEntity buildBadRequest(Throwable ex) {
        ErrorEntity serviceError;
        Class<? extends Throwable> exCls = ex.getClass();
        if (ConstraintViolationException.class.isAssignableFrom(exCls)) {
            serviceError = handleConstraintViolationException((ConstraintViolationException) ex);
        } else if (MethodArgumentNotValidException.class.isAssignableFrom(exCls)) {
            serviceError = handleMethodArgumentNotValidException((MethodArgumentNotValidException) ex);
        } else if (BindException.class.isAssignableFrom(exCls)) {
            serviceError = handleBindException((BindException) ex);
        } else if (MissingServletRequestParameterException.class.isAssignableFrom(exCls)) {
            serviceError = handleMissingServletRequestParameterException((MissingServletRequestParameterException) ex);
        } else if (MethodArgumentTypeMismatchException.class.isAssignableFrom(exCls)) {
            serviceError = handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex);
        } else {
            serviceError = new ErrorEntity();
            serviceError.setCode(Code.ERROR);
            serviceError.setMsg(this.getMessage(ex));
        }
        serviceError.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return serviceError;
    }

    /**
     * <p>
     * Title: 创建未认证(401)异常响应对象
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param ex 异常
     * @return com.hodgepodge.exception.ErrorEntity
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected ErrorEntity buildUnauthorized(Throwable ex) {
        ErrorEntity error = new ErrorEntity();
        error.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        error.setCode(Code.UNAUTHORIZED);
        error.setMsg(this.getMessage(ex));
        return error;
    }

    /**
     * <p>
     * Title: 创建访问权限不足(403)异常响应对象
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return com.hodgepodge.exception.ErrorEntity
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected ErrorEntity buildForbidden() {
        ErrorEntity error = new ErrorEntity();
        error.setStatusCode(HttpStatus.FORBIDDEN.value());
        error.setCode(Code.FORBIDDEN);
        error.setMsg("无操作权限");
        return error;
    }

    /**
     * <p>
     * Title: 创建服务未找到(404)异常响应对象
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return com.hodgepodge.exception.ErrorEntity
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected ErrorEntity buildNotFound() {
        ErrorEntity error = new ErrorEntity();
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setCode(Code.NOT_EXIST);
        error.setMsg("请求目标不存在");
        return error;
    }

    /**
     * <p>
     * Title: 创建请求方法不支持(405)异常响应对象
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return com.hodgepodge.exception.ErrorEntity
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected ErrorEntity buildMethodNotAllowed() {
        ErrorEntity error = new ErrorEntity();
        error.setStatusCode(HttpStatus.METHOD_NOT_ALLOWED.value());
        error.setCode(Code.NOT_ALLOWED);
        error.setMsg("无效请求方法");
        return error;
    }

    /**
     * <p>
     * Title: 创建Media类型不允许(415)异常响应对象
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return com.hodgepodge.exception.ErrorEntity
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected ErrorEntity buildMediaTypeNotSupported() {
        ErrorEntity error = new ErrorEntity();
        error.setStatusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        error.setCode(Code.NOT_SUPPORT);
        error.setMsg("无效请求格式");
        return error;
    }

    /**
     * <p>
     * Title: 创建服务无实现(501)异常响应对象
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return com.hodgepodge.exception.ErrorEntity
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected ErrorEntity buildNotImplemented() {
        ErrorEntity error = new ErrorEntity();
        error.setStatusCode(HttpStatus.NOT_IMPLEMENTED.value());
        error.setCode(Code.UNAVAILABLE);
        error.setMsg("服务无实现");
        return error;
    }

    /**
     * <p>
     * Title: 创建网关错误(502)异常响应对象
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return com.hodgepodge.exception.ErrorEntity
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected ErrorEntity buildBadGateway() {
        ErrorEntity error = new ErrorEntity();
        error.setStatusCode(HttpStatus.BAD_GATEWAY.value());
        error.setCode(Code.UNAVAILABLE);
        error.setMsg("网关异常");
        return error;
    }

    /**
     * <p>
     * Title: 创建服务不可用(503)异常响应对象
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return com.hodgepodge.exception.ErrorEntity
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected ErrorEntity buildServiceUnavailable() {
        ErrorEntity error = new ErrorEntity();
        error.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE.value());
        error.setCode(Code.UNAVAILABLE);
        error.setMsg("服务暂不可用");
        return error;
    }

    /**
     * <p>
     * Title: 创建网关超时(504)异常响应对象<
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return com.hodgepodge.exception.ErrorEntity
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected ErrorEntity buildGatewayTimeout() {
        ErrorEntity error = new ErrorEntity();
        error.setStatusCode(HttpStatus.GATEWAY_TIMEOUT.value());
        error.setCode(Code.TIMEOUT);
        error.setMsg("网关超时");
        return error;
    }

    /**
     * <p>
     * Title: 获取错误提示
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param ex
     * @return java.lang.String
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected String getMessage(Throwable ex) {
        String msgPrefix = "内部错误:";
        if (ex == null) {
            return msgPrefix + "UNKNOWN";
        }
        if (NullPointerException.class.isAssignableFrom(ex.getClass())) {
            return msgPrefix + "NULL";
        } else if (GlobalExceptionHelper.isSqlException(ex)) {
            // 数据库执行异常
            return msgPrefix + "数据库执行异常";
        } else if (HttpMessageNotReadableException.class.isAssignableFrom(ex.getClass())) {
            return "请求体格式不正确";
        } else if (HttpMessageNotWritableException.class.isAssignableFrom(ex.getClass())) {
            return msgPrefix + "响应体格式不正确";
        } else {
            return ex.getMessage();
        }
    }

    /**
     * <p>
     * Title: 创建500异常响应对象
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param ex 异常
     * @return com.hodgepodge.exception.ErrorEntity
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected ErrorEntity buildInternalServer(Throwable ex) {
        ErrorEntity error = new ErrorEntity();
        error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setCode(Code.ERROR);
        error.setMsg(this.getMessage(ex));
        return error;
    }

    /**
     * <p>
     * Title: 处理验证异常(400)
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param ex 异常
     * @return com.hodgepodge.exception.ErrorEntity
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected ErrorEntity handleConstraintViolationException(ConstraintViolationException ex) {
        List<FieldError> fieldErrors = new ArrayList<>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        violations.forEach(violation -> {
            FieldError error = new FieldError("",
                    determineField(violation),
                    violation.getInvalidValue(),
                    true,
                    new String[]{violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName()},
                    violation.getExecutableParameters(),
                    violation.getMessage());
            fieldErrors.add(error);
        });
        return convertValidationError(fieldErrors);
    }

    /**
     * <p>
     * Title: 转换验证的属性路径
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param violation 验证信息
     * @return java.lang.String
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected String determineField(ConstraintViolation<?> violation) {
        Path path = violation.getPropertyPath();
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Path.Node node : path) {
            if (node.isInIterable()) {
                sb.append('[');
                Object index = node.getIndex();
                if (index == null) {
                    index = node.getKey();
                }
                if (index != null) {
                    sb.append(index);
                }
                sb.append(']');
            }
            String name = node.getName();
            if (name != null
                    && (node.getKind() == ElementKind.PROPERTY || node.getKind() == ElementKind.PARAMETER)
                    && !name.startsWith("<")) {
                if (!first) {
                    sb.append('.');
                }
                first = false;
                sb.append(name);
            }
        }
        return sb.toString();
    }

    /**
     * <p>
     * Title: 处理接口参数验证异常(400)
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param ex 异常
     * @return com.hodgepodge.exception.ErrorEntity
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected ErrorEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return convertValidationError(ex.getBindingResult().getFieldErrors());
    }

    /**
     * <p>
     * Title: 处理接口参数绑定异常(400)
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param ex 异常
     * @return java.lang.Error
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected ErrorEntity handleBindException(BindException ex) {
        return convertValidationError(ex.getFieldErrors());
    }

    /**
     * <p>
     * Title: 处理请求参数缺失异常(400)
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param ex 异常
     * @return com.hodgepodge.exception.ErrorEntity
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected ErrorEntity handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        ErrorEntity serviceError = new ErrorEntity();
        serviceError.setSubject(ex.getParameterName());
        serviceError.setCode(Code.NOT_NULL);
        serviceError.setMsg(ex.getParameterName() + "不能为空");
        return serviceError;
    }

    /**
     * <p>
     * Title: 处理请求参数转换异常(400)
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param ex 异常
     * @return com.hodgepodge.exception.ErrorEntity
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected ErrorEntity handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ErrorEntity serviceError = new ErrorEntity();
        serviceError.setSubject(ex.getName());
        serviceError.setCode(Code.UNAVAILABLE);
        serviceError.setMsg(ex.getName() + "类型不正确");
        return serviceError;
    }

    /**
     * <p>
     * Title: FieldError转换为服务异常对象
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param fieldErrors
     * @return com.hodgepodge.exception.ErrorEntity
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected ErrorEntity convertValidationError(List<FieldError> fieldErrors) {
        ErrorEntity serviceError = new ErrorEntity();
        List<BaseErrorEntity> extras = new ArrayList<>();
        serviceError.setExtras(extras);
        fieldErrors.forEach((error) -> {
            String code = error.getCode(), msg = error.getDefaultMessage();
            BaseErrorEntity payload;
            if (ObjectUtils.isEmpty(serviceError.getCode())) {
                payload = serviceError;
            } else {
                payload = new BaseErrorEntity();
                extras.add(payload);
            }
            payload.setMsg(msg);
            payload.setSubject(error.getField());
            if (Null.class.getSimpleName().equals(code)) {
                payload.setCode(Code.MUST_NULL);
            } else if (NotBlank.class.getSimpleName().equals(code)) {
                payload.setCode(Code.NOT_BLANK);
            } else if (NotNull.class.getSimpleName().equals(code)) {
                payload.setCode(Code.NOT_NULL);
            } else if (NotEmpty.class.getSimpleName().equals(code)) {
                payload.setCode(Code.NOT_EMPTY);
            } else if (AssertTrue.class.getSimpleName().equals(code)) {
                payload.setCode(Code.ASSERT_TRUE);
            } else if (AssertFalse.class.getSimpleName().equals(code)) {
                payload.setCode(Code.ASSERT_FALSE);
            } else if (Min.class.getSimpleName().equals(code)) {
                payload.setCode(Code.MIN);
            } else if (Max.class.getSimpleName().equals(code)) {
                payload.setCode(Code.MAX);
            } else if (Size.class.getSimpleName().equals(code)) {
                payload.setCode(Code.LENGTH);
            } else if (DecimalMin.class.getSimpleName().equals(code)) {
                payload.setCode(Code.MIN);
            } else if (DecimalMax.class.getSimpleName().equals(code)) {
                payload.setCode(Code.MAX);
            } else if (Digits.class.getSimpleName().equals(code)) {
                payload.setCode(Code.DIGITS);
            } else if (Past.class.getSimpleName().equals(code)) {
                payload.setCode(Code.PAST);
            } else if (Future.class.getSimpleName().equals(code)) {
                payload.setCode(Code.FUTURE);
            } else if (Pattern.class.getSimpleName().equals(code)) {
                payload.setCode(Code.PATTERN);
            } else if (Email.class.getSimpleName().equals(code)) {
                payload.setCode(Code.EMAIL);
            } else if (TypeMismatchException.ERROR_CODE.equals(code)) {
                payload.setCode(Code.TYPE_MIS_MATCH);
                payload.setMsg(error.getField() + "值[" + error.getRejectedValue() + "]类型转换失败");
            } else {
                payload.setCode(code);
            }
        });
        return serviceError;
    }

    /**
     * <p>Title: 打印异常日志</p>
     * <p>Description: </p>
     *
     * @date 2020年05月15日
     * @author 余新引
     */
    public void printLog(Throwable ex, int httpStatus, Object result) {
        printLog(ex, null, httpStatus, result);
    }

    /**
     * <p>
     * Title: 打印异常日志
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param ex         异常
     * @param request    请求
     * @param httpStatus 状态码
     * @param result
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    public void printLog(Throwable ex, HttpServletRequest request, int httpStatus, Object result) {
        Throwable unwrapException = GlobalExceptionHelper.unwrapException(ex);
        String url = "", headers = "", query = "", method = "", body = "";
        if (request != null) {
            method = request.getMethod();
            url = getRequestUrl(request);
            body = getBody(request);
            query = getQuery(request);
            headers = getHeader(request);
        }
        List<Object> args = new ArrayList<>();
        Collections.addAll(args, httpStatus, url, query, method, headers, body, Optional.ofNullable(unwrapException).map(Throwable::getMessage).orElse(""), result);
        // 是否需要打印异常堆栈
        if (needPrintStackTrace(unwrapException)) {
            args.add(unwrapException);
        } else if (unwrapException != null) {
            // 打印已知异常的调用链
            printExceptionRoute(unwrapException);
        }
        log.error("\r\n>>>请求服务异常 [{}]\r\n>Url: {}\r\n>Query: {}\r\n>Method: {}\r\n>Headers: {}\r\n>Body: {}\r\n>Reason: {}\r\n>Result: {}\r\n<<<", args.toArray());
        // 如果开启了DEBUG日志,则打印全部异常堆栈
        if (log.isDebugEnabled() && ex != null) {
            log.error(ex.getMessage(), ex);
        }
    }

    protected String getBody(HttpServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper) {
            try {
                return new String(((ContentCachingRequestWrapper) request).getContentAsByteArray(), StandardCharsets.UTF_8);
            } catch (Exception e) {
                log.error("Parse body error, {}", e.getMessage(), e);
                return "<parse error>";
            }
        }
        return "";
    }

    protected String getHeader(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames.hasMoreElements()) {
            headers.append("\r\n");
        }
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            headers
                    .append("    ")
                    .append(key)
                    .append(": ")
                    .append(request.getHeader(key))
                    .append("\r\n");
        }
        return headers.toString();
    }

    protected String getQuery(HttpServletRequest request) {
        StringBuilder query = new StringBuilder();
        Enumeration<String> queryNames = request.getParameterNames();
        if (queryNames.hasMoreElements()) {
            query.append("\r\n");
        }
        while (queryNames.hasMoreElements()) {
            String key = queryNames.nextElement();
            query
                    .append("    ")
                    .append(key)
                    .append("=")
                    .append(StringUtils.arrayToCommaDelimitedString(request.getParameterValues(key)))
                    .append("\r\n");
        }
        return query.toString();
    }

    /**
     * <p>
     * Title: 是否需要打印异常堆栈
     * </p>
     * <p>
     * Description: 默认不打印客户端异常
     * </p>
     *
     * @param ex
     * @return boolean
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected boolean needPrintStackTrace(Throwable ex) {
        return !(ex == null
                || ServiceRuntimeException.class.isAssignableFrom(ex.getClass())
                || ExceptionTypeMatcher.matchesException(ex.getClass(), ExceptionTypeMatcher.BAD_REQUEST_EXCEPTION_CLASS)
                || ExceptionTypeMatcher.matchesException(ex.getClass(), ExceptionTypeMatcher.UNAUTHORIZED_EXCEPTION_CLASS)
                || ExceptionTypeMatcher.matchesException(ex.getClass(), ExceptionTypeMatcher.FORBIDDEN_EXCEPTION_CLASS)
                || ExceptionTypeMatcher.matchesException(ex.getClass(), ExceptionTypeMatcher.NOT_FOUND_EXCEPTION_CLASS)
                || ExceptionTypeMatcher.matchesException(ex.getClass(), ExceptionTypeMatcher.METHOD_NOT_ALLOWED_EXCEPTION_CLASS)
                || ExceptionTypeMatcher.matchesException(ex.getClass(), ExceptionTypeMatcher.UNSUPPORTED_MEDIA_TYPE_EXCEPTION_CLASS));
    }

    /**
     * <p>
     * Title: 获取请求的路径
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param request 请求
     * @return java.lang.String
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    protected String getRequestUrl(HttpServletRequest request) {
        return request.getScheme()
                + "://"
                + request.getServerName()
                + ":" + request.getServerPort()
                + (StringUtils.hasText(request.getContextPath()) ? ("/" + request.getContextPath()) : "")
                + request.getRequestURI();
    }

    /**
     * 默认的已知异常类前缀
     */
    protected final String PRINT_PACKAGE_PREFIX = "com.zg.";

    /**
     * <p>Title: 打印异常调用链</p>
     * <p>Description: 用于打印代码中已知的异常类调用链,防止日志中出现复杂和冗余的堆栈信息</p>
     *
     * @param ex 异常类
     * @date 2020年03月16日
     * @author 余新引
     */
    public void printExceptionRoute(Throwable ex) {
        GlobalExceptionHelper.printExceptionRoute(PRINT_PACKAGE_PREFIX, ex);
    }


}
