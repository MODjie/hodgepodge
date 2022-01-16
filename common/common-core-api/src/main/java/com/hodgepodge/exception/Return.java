package com.hodgepodge.exception;

/**
 * <p>
 * Title: Return
 * </p>
 * <p>
 * Description:
 * 服务方法异常结果构建工具类 支持链式调用方式返回异常结果，
 * 例如 // 显式抛出方式，后面不允许有代码，一般情况使用此方式 throw Return.server().build("服务器异常消息");
 * // 隐式抛出方式，后面可以有代码，用于三元表达式等 Return.server().build("服务器异常消息").doThrow();
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月13日
 * @since 1.8
 */
public class Return {



    /**
     * <p>
     * Title: 返回服务端异常构建器
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return com.hodgepodge.exception.ReturnServerErrorBuilder
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static ReturnServerErrorBuilder server() {
        return server(null, null);
    }

    /**
     * <p>
     * Title: 返回服务端异常构建器
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param cause
     * @return com.hodgepodge.exception.ReturnServerErrorBuilder
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static ReturnServerErrorBuilder server(Throwable cause) {
        return server(null, cause);
    }

    /**
     * <p>
     * Title: 返回服务端异常构建器
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param msg 错误信息
     * @param cause 异常
     * @return com.hodgepodge.exception.ReturnServerErrorBuilder
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static ReturnServerErrorBuilder server(String msg, Throwable cause) {
        return new ReturnServerErrorBuilder().msg(msg).cause(cause);
    }

    /**
     * <p>
     * Title: 返回客户端异常构建器
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return com.hodgepodge.exception.ReturnClientErrorBuilder
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static ReturnClientErrorBuilder client() {
        return client(null);
    }

    /**
     * <p>
     * Title: 返回客户端异常构建器
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param msg 错误信息
     * @return com.hodgepodge.exception.ReturnClientErrorBuilder
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static ReturnClientErrorBuilder client(String msg) {
        return client(msg, null);
    }

    /**
     * <p>
     * Title: 返回客户端异常构建器
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param msg 错误信息
     * @param subject 错误主体
     * @return com.hodgepodge.exception.ReturnClientErrorBuilder
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public static ReturnClientErrorBuilder client(String msg, String subject) {
        return new ReturnClientErrorBuilder().msg(msg).subject(subject);
    }

}
