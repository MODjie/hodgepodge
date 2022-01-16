package com.hodgepodge.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Optional;

/**
 * <p>
 * Title: ReturnException
 * </p>
 * <p>
 * Description: 用于抛出Return结果的异常类
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月13日
 * @since 1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReturnException extends ServiceRuntimeException {

    private static final long serialVersionUID = -7416627591446077908L;

    private ErrorEntity error;

    public ReturnException(ErrorEntity error) {
        this(error, null);
    }

    public ReturnException(ErrorEntity error, Throwable cause) {
        super(Optional.ofNullable(error).map(ErrorEntity::getMsg).orElse(null), cause);
        this.error = error;
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
        throw this;
    }

}
