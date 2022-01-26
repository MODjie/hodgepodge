package com.hodgepodge.exception;

/**
 * <p>
 * Title: ServiceRuntimeException
 * </p>
 * <p>
 * Description: 自定义服务运行时异常
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月13日
 * @since 1.8
 */
public class ServiceRuntimeException extends RuntimeException{

    private static final long serialVersionUID = -6739704645097068943L;

    public ServiceRuntimeException() {
        super();
    }

    public ServiceRuntimeException(String message) {
        super(message);
    }

    public ServiceRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceRuntimeException(Throwable cause) {
        super(cause);
    }

    protected ServiceRuntimeException(String message, Throwable cause,
                                      boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
