package com.hodgepodge.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * <p>
 * Title: ReturnErrorBuilder
 * </p>
 * <p>
 * Description: 返回错误构建器
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月13日
 * @since 1.8
 */
public class ReturnErrorBuilder<T extends ReturnErrorBuilder<?>> {

    /**
     * 错误实体
     */
    protected final ErrorEntity error = new ErrorEntity();

    /**
     * 异常
     */
    protected Throwable cause;

    /**
     * <p>
     * Title: 设置错误编码
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param code 错误编码
     * @return T
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public T code(String code){
        this.error.setCode(code);
        return (T) this;
    }

    /**
     * <p>
     * Title: 设置错误信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param msg 错误信息
     * @return T
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public T msg(String msg){
        this.error.setMsg(msg);
        return (T) this;
    }

    /**
     * <p>
     * Title: 设置错误主体
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param subject 错误主体
     * @return T
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public T subject(String subject){
        this.error.setSubject(subject);
        return (T) this;
    }

    /**
     * <p>
     * Title: 设置拓展异常信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param extras 拓展异常信息
     * @return T
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public T extras(List<BaseErrorEntity> extras){
        this.error.setExtras(extras);
        return (T) this;
    }

    /**
     * <p>
     * Title: 设置状态码
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param statusCode 状态码
     * @return T
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public T statusCode(int statusCode){
        this.error.setStatusCode(statusCode);
        return (T) this;
    }

    /**
     * <p>
     * Title: 设置状态码
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param httpStatus http状态码
     * @return T
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public T statusCode(HttpStatus httpStatus){
        this.statusCode(httpStatus.value());
        return (T) this;
    }

    /**
     * <p>
     * Title: 设置异常
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param cause 异常
     * @return T
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public T cause(Throwable cause){
        this.cause = cause;
        return (T) this;
    }

    /**
     * <p>
     * Title: 返回错误实体对象
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return com.hodgepodge.exception.ErrorEntity
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public ErrorEntity get(){
        return this.error;
    }


    /**
     * <p>
     * Title: 构建异常返回
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param throwable 异常
     * @return com.hodgepodge.exception.ReturnException
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public ReturnException build(Throwable throwable) {
        return this.build(null, null, throwable);
    }

    /**
     * <p>
     * Title: 构建异常返回
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param msg 错误信息
     * @return com.hodgepodge.exception.ReturnException
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public ReturnException build(String msg) {
        return build(msg, (Throwable) null);
    }

    /**
     * <p>
     * Title: 构建异常返回
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param code 错误编码
     * @param msg 错误信息
     * @return com.hodgepodge.exception.ReturnException
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public ReturnException build(String code, String msg) {
        return build(code, msg, null);
    }

    /**
     * <p>
     * Title: 构建异常返回
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param msg 错误信息
     * @param throwable 异常
     * @return com.hodgepodge.exception.ReturnException
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public ReturnException build(String msg, Throwable throwable) {
        return build(null, null, msg, null, throwable);
    }

    /**
     * <p>
     * Title: 构建异常返回
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param code 错误编码
     * @param msg 错误信息
     * @param throwable 异常
     * @return com.hodgepodge.exception.ReturnException
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public ReturnException build(String code, String msg, Throwable throwable) {
        return this.build(null, code, msg, null, throwable);
    }


    /**
     * <p>
     * Title: 构建异常返回
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param subject 错误主体
     * @param code 错误编码
     * @param extras 拓展异常
     * @param throwable 异常
     * @return com.hodgepodge.exception.ReturnException
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public ReturnException build(String subject,String code,String msg, List<BaseErrorEntity> extras, Throwable throwable){
        return this.subject(subject).code(code).msg(msg).extras(extras).cause(throwable).build();
    }

    /**
     * <p>
     * Title: 构建异常返回
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return com.hodgepodge.exception.ReturnException
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public ReturnException build(){
        //默认服务器内部异常
        if (this.error.getStatusCode() <= 0){
            this.statusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ReturnException(this.error,this.cause);
    }

    /**
     * <p>
     * Title: 抛出异常
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @author 刘小杰
     * @date 2022年01月13日
     * @since 1.8
     */
    public void doThrow() {
        this.build().doThrow();
    }
}
