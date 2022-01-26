package com.hodgepodge.exception;

import org.springframework.http.HttpStatus;

/**
 * <p>
 * Title: ReturnClientErrorBuilder
 * </p>
 * <p>
 * Description: 返回客户端错误构建器
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月13日
 * @since 1.8
 */
public class ReturnClientErrorBuilder extends ReturnErrorBuilder<ReturnClientErrorBuilder> {

    /**
     * <p>
     * Title: 返回未认证异常（401）
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
    public ReturnClientErrorBuilder unauthorized(){
        return this.code(Code.UNAUTHORIZED).statusCode(HttpStatus.UNAUTHORIZED);
    }

    /**
     * <p>
     * Title: 返回未认证异常（401）
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
    public ReturnClientErrorBuilder unauthorized(String msg){
        return this.unauthorized().msg(msg);
    }

    /**
     * <p>
     * Title: 返回未认证异常（401）
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
    public ReturnClientErrorBuilder unauthorized(String msg, String subject) {
        return unauthorized(msg).subject(subject);
    }

    /**
     * <p>
     * Title: 返回权限不足异常（403）
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
    public ReturnClientErrorBuilder accessDenied() {
        return this.code(Code.FORBIDDEN).statusCode(HttpStatus.FORBIDDEN);
    }

    /**
     * <p>
     * Title: 返回权限不足异常(403)
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
    public ReturnClientErrorBuilder accessDenied(String msg) {
        return accessDenied().msg(msg);
    }

    /**
     * <p>
     * Title: 返回权限不足异常(403)
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
    public ReturnClientErrorBuilder accessDenied(String msg, String subject) {
        return accessDenied(msg).subject(subject);
    }

    /**
     * <p>
     * Title: 返回请求实体无法处理异常(422)
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
    public ReturnClientErrorBuilder unprocessable() {
        return this.statusCode(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * <p>
     * Title: 返回请求实体无法处理异常(422)
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
    public ReturnClientErrorBuilder unprocessable(String msg) {
        return unprocessable().msg(msg);
    }

    /**
     * <p>
     * Title: 返回请求实体无法处理异常(422)
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
    public ReturnClientErrorBuilder unprocessable(String msg, String subject) {
        return unprocessable(msg).subject(subject);
    }

    /**
     * <p>
     * Title: 返回请求参数不正确异常(400)
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
    public ReturnClientErrorBuilder badRequest() {
        return this.statusCode(HttpStatus.BAD_REQUEST);
    }

    /**
     * <p>
     * Title: 返回请求参数不正确异常(400)
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
    public ReturnClientErrorBuilder badRequest(String msg) {
        return badRequest().msg(msg);
    }

    /**
     * <p>
     * Title: 返回请求参数不正确异常(400)
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
    public ReturnClientErrorBuilder badRequest(String msg, String subject) {
        return badRequest(msg).subject(subject);
    }
}
