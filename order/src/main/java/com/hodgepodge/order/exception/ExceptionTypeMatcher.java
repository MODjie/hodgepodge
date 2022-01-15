package com.hodgepodge.order.exception;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * Title: 异常类型匹配类
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author <刘小杰>
 * @date 2022年01月15日
 * @since 1.8
 */
public class ExceptionTypeMatcher {

    /**
     * 包裹的异常
     */
    public static final List<String> WRAPPED_EXCEPTION_CLASS = Arrays.asList(
            "java.lang.reflect.InvocationTargetException",
            "java.lang.reflect.UndeclaredThrowableException",
            "feign.codec.DecodeException"
    );
    /**
     * 400异常
     */
    public static final List<String> BAD_REQUEST_EXCEPTION_CLASS = Arrays.asList(
            "javax.validation.ConstraintViolationException",
            "org.springframework.web.bind.MethodArgumentNotValidException",
            "org.springframework.validation.BindException",
            "org.springframework.http.converter.HttpMessageNotReadableException",
            "org.springframework.web.multipart.support.MissingServletRequestPartException",
            "org.springframework.beans.TypeMismatchException",
            "org.springframework.web.bind.ServletRequestBindingException",
            "org.springframework.web.bind.MissingServletRequestParameterException"
    );
    /**
     * 401异常
     */
    public static final List<String> UNAUTHORIZED_EXCEPTION_CLASS = Collections.singletonList(
            "org.springframework.security.core.AuthenticationException"
    );

    /**
     * 403异常
     */
    public static final List<String> FORBIDDEN_EXCEPTION_CLASS = Arrays.asList(
            "org.springframework.security.access.AccessDeniedException",
            "org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException",
            "org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException"
    );
    /**
     * 404异常
     */
    public static final List<String> NOT_FOUND_EXCEPTION_CLASS = Collections.singletonList(
            "org.springframework.web.servlet.NoHandlerFoundException"
    );

    /**
     * 405异常
     */
    public static final List<String> METHOD_NOT_ALLOWED_EXCEPTION_CLASS = Arrays.asList(
            "org.springframework.web.HttpRequestMethodNotSupportedException",
            "org.springframework.web.server.MethodNotAllowedException"
    );
    /**
     * 415异常
     */
    public static final List<String> UNSUPPORTED_MEDIA_TYPE_EXCEPTION_CLASS = Collections.singletonList(
            "org.springframework.web.HttpMediaTypeNotSupportedException"
    );

    /**
     * 501异常
     */
    public static final List<String> NOT_IMPLEMENTED_EXCEPTION_CLASS = Arrays.asList(
            "org.springframework.web.client.HttpServerErrorException.NotImplemented"
    );
    /**
     * 502异常
     */
    public static final List<String> BAD_GATEWAY_EXCEPTION_CLASS = Collections.singletonList(
            "HttpServerErrorException.BadGateway"
    );

    /**
     * 503异常
     */
    public static final List<String> SERVICE_UNAVAILABLE_EXCEPTION_CLASS = Arrays.asList(
            "org.springframework.web.client.HttpServerErrorException.ServiceUnavailable",
            "org.springframework.web.context.request.async.AsyncRequestTimeoutException"
    );
    /**
     * 504异常
     */
    public static final List<String> GATEWAY_TIMEOUT_EXCEPTION_CLASS = Collections.singletonList(
            "org.springframework.web.client.HttpServerErrorException.GatewayTimeout"
    );


    /**
     * <p>
     * Title: 异常类型匹配
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param exClass 异常类的class
     * @param parent  异常父类
     * @return boolean
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    public static boolean matchesException(Class<?> exClass, String... parent) {
        return matchesException(exClass, Arrays.asList(parent));
    }

    /**
     * <p>
     * Title: 异常类型匹配
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param exClass 异常类的class
     * @param parent  异常父类
     * @return boolean
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    public static boolean matchesException(Class<?> exClass, Collection<String> parent) {
        return matchesException(exClass, parent
                .stream()
                .map(c -> {
                    try {
                        return Class.forName(c);
                    } catch (Throwable e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }


    /**
     * <p>Title: 异常类型匹配</p>
     * <p>Description: </p>
     *
     * @return boolean
     * @date 2020年04月16日
     * @author 余新引
     */
    public static boolean matchesException(Class<?> exClass, Class<?>... parent) {
        return matchesException(exClass, Arrays.asList(parent));
    }

    /**
     * <p>
     * Title: 异常类型匹配
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param exClass
     * @param parents
     * @return boolean
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    public static boolean matchesException(Class<?> exClass, List<Class<?>> parents) {
        return Optional
                .ofNullable(parents)
                .map(List::stream)
                .map(s -> s.anyMatch(c -> c.isAssignableFrom(exClass)))
                .orElse(false);
    }
}
