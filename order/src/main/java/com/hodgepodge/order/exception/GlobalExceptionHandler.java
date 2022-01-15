package com.hodgepodge.order.exception;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Title: 全局异常处理器
 * </p>
 * <p>
 * Description:通过自定义GlobalExceptionHandlerAdapter 类自定义响应内容
 * </p>
 *
 * @author <刘小杰>
 * @date 2022年01月15日
 * @since 1.8
 */
@Order
@Slf4j
@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    protected final GlobalExceptionHandlerAdapter adapter;

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<?> resolveException(HttpServletRequest request, Throwable ex) {
        try {
            return handleException(request, ex);
        } catch (Throwable newEx) {
            return handleException(request, newEx);
        }
    }

    private ResponseEntity<?> handleException(HttpServletRequest request, Throwable ex) {
        if (adapter == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return adapter.handleException(request, ex);
    }

}
