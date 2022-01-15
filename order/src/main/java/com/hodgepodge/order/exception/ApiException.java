package com.hodgepodge.order.exception;

import com.hodgepodge.exception.ErrorEntity;
import com.hodgepodge.exception.ServiceRuntimeException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.Objects;

/**
 * <p>
 * Title: 接口调用异常
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author <刘小杰>
 * @date 2022年01月15日
 * @since 1.8
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ApiException extends ServiceRuntimeException {

    private static final long serialVersionUID = 6177736069460955369L;

    /**
     * 响应体
     */
    private ErrorEntity apiError;

    /**
     * 接口响应体
     */
    private ErrorEntity responseError;

    /**
     * 响应码
     */
    private HttpStatus responseStatus;

    /**
     * 响应字符串
     */
    private String responseBody;

    public ApiException(ErrorEntity apiError, HttpStatus responseStatus, String responseBody) {
        this.apiError = apiError;
        this.responseStatus = responseStatus;
        this.responseBody = responseBody;
    }

    /**
     * <p>
     * Title: 设置错误链路跟踪
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param responseError
     * @return com.hodgepodge.order.exception.ApiException
     * @author 刘小杰
     * @date 2022年01月15日
     * @since 1.8
     */
    public ApiException trace(ErrorEntity responseError) {
        if (this.apiError != null && responseError != null && Objects.equals(this.apiError, responseError)) {
            this.responseError = responseError;
            this.apiError.setTrace(responseError);
        }
        return this;
    }

}
