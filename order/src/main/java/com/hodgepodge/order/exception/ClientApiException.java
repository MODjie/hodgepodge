package com.hodgepodge.order.exception;

import com.hodgepodge.exception.ErrorEntity;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * Title: 内部接口返回的客户端(40x)异常
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author <刘小杰>
 * @date 2022年01月15日
 * @since 1.8
 */
public class ClientApiException extends ApiException {

    private static final long serialVersionUID = 1724721632773044407L;

    public ClientApiException(ErrorEntity apiError, HttpStatus responseStatus, String responseBody) {
        super(apiError, responseStatus, responseBody);
    }
}
