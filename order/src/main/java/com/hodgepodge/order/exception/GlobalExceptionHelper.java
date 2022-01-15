package com.hodgepodge.order.exception;

import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * Title: 全局异常处理帮助类
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author <刘小杰>
 * @date 2022年01月15日
 * @since 1.8
 */
@Slf4j
public class GlobalExceptionHelper {
    /**
     * <p>
     * Title: 判断是否为数据库异常
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param ex 异常
     * @return boolean
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    public static boolean isSqlException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        Class<?> cls = ex.getClass();
        boolean result = false;
        try {
            result = Class.forName("org.springframework.dao.DataAccessException").isAssignableFrom(cls);
        } catch (ClassNotFoundException ignored) {
        }
        if (!result) {
            result = SQLException.class.isAssignableFrom(cls);
        }
        return result;
    }

    /**
     * <p>
     * Title: 打印异常调用链
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param prefix 类前缀过滤器
     * @param ex     异常类
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    public static void printExceptionRoute(String prefix, Throwable ex) {
        if (ex == null) {
            return;
        }
        Throwable firstException = ex;
        while (firstException.getCause() != null) {
            firstException = firstException.getCause();
        }
        final List<String> prefixList = Arrays.asList(prefix.split(","));
        List<String> traces = Arrays.stream(firstException.getStackTrace())
                .filter(ele -> prefixList
                        .stream()
                        .anyMatch(p -> ele.getClassName().startsWith(p)))
                .map(GlobalExceptionHelper::getTraceInfo)
                .collect(Collectors.toList());
        StringBuilder msg = new StringBuilder("\r\n>>>Exception Traces (Filter=\"{}\"): {}\r\n\r\n");
        for (int i = 0; i < traces.size(); i++) {
            if (i != 0) {
                msg.append("\t\t\t\t\t\t↑↑↑↑↑\r\n");
            }
            msg.append(traces.get(i));
        }
        boolean result = traces.size() > 0;
        if (result) {
            log.error(msg.append("<<<\r\n").toString(), prefix, ex.getMessage());
        }
    }

    private static String getTraceInfo(StackTraceElement ele) {
        String className = ele.getClassName().substring(ele.getClassName().lastIndexOf(".") + 1);
        return ele.getClassName() + "." + ele.getMethodName() + "(" + className + ".java:" + ele.getLineNumber() + ") \r\n";
    }

    /**
     * <p>
     * Title: 解构出真实的异常
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param ex 异常类
     * @return java.lang.Throwable
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    public static Throwable unwrapException(Throwable ex) {
        if (ex == null) {
            return null;
        }
        if (ExceptionTypeMatcher.matchesException(ex.getClass(), ExceptionTypeMatcher.WRAPPED_EXCEPTION_CLASS)) {
            return Optional.ofNullable(ex.getCause()).map(GlobalExceptionHelper::unwrapException).orElse(ex);
        }
        return ex;
    }

}
