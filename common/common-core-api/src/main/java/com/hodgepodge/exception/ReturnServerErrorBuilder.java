package com.hodgepodge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * Title: ReturnServerErrorBuilder
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月13日
 * @since 1.8
 */
public class ReturnServerErrorBuilder extends ReturnErrorBuilder<ReturnServerErrorBuilder> {


    /**
     * <p>
     * Title: 返回服务异常(500)
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
    public ReturnServerErrorBuilder error() {
        return this.code(Code.ERROR).statusCode(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * <p>
     * Title: 返回服务异常(500)
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param msg 错误信息
     * @return com.hodgepodge.exception.ReturnServerErrorBuilder
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public ReturnServerErrorBuilder error(String msg) {
        return this.error().msg(msg);
    }

    /**
     * <p>
     * Title: 返回服务异常(500)
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param msg     错误信息
     * @param subject 错误主体
     * @return com.hodgepodge.exception.ReturnServerErrorBuilder
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public ReturnServerErrorBuilder error(String msg, String subject) {
        return this.error(msg).subject(subject);
    }

    /**
     * <p>
     * Title: 返回服务无实现异常(501)
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
    public ReturnServerErrorBuilder notImplemented() {
        return this.code(Code.UNAVAILABLE).statusCode(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * <p>
     * Title: 返回服务无实现异常(501)
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param msg 错误信息
     * @return com.hodgepodge.exception.ReturnServerErrorBuilder
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public ReturnServerErrorBuilder notImplemented(String msg) {
        return this.notImplemented().msg(ObjectUtils.isEmpty(msg) ? "服务无实现" : msg);
    }

    /**
     * <p>
     * Title: 返回服务无实现异常(501)
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param msg     错误信息
     * @param subject 错误主体
     * @return com.hodgepodge.exception.ReturnServerErrorBuilder
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public ReturnServerErrorBuilder notImplemented(String msg, String subject) {
        return this.notImplemented(msg).subject(subject);
    }

    /**
     * <p>
     * Title: 返回服务不可用异常(503)
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
    public ReturnServerErrorBuilder unavailable() {
        return this.code(Code.UNAVAILABLE).statusCode(HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * <p>
     * Title: 返回服务不可用异常(503)
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param msg 错误信息
     * @return com.hodgepodge.exception.ReturnServerErrorBuilder
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public ReturnServerErrorBuilder unavailable(String msg) {
        return this.unavailable().msg(ObjectUtils.isEmpty(msg) ? "服务暂不可用" : msg);
    }

    /**
     * <p>
     * Title: 返回服务不可用异常(503)
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param msg 错误信息
     * @param subject 错误主体
     * @return com.hodgepodge.exception.ReturnServerErrorBuilder
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public ReturnServerErrorBuilder unavailable(String msg, String subject) {
        return this.unavailable(msg).subject(subject);
    }

}
