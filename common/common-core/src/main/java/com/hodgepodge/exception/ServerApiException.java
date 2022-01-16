package com.hodgepodge.exception;

import org.springframework.http.HttpStatus;

/**
 * <p>
 * Title: 内部接口返回的服务端(50x)异常
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author <刘小杰>
 * @date 2022年01月15日
 * @since 1.8
 */
public class ServerApiException extends ApiException {

    private static final long serialVersionUID = -8515967521737955750L;

    public ServerApiException(ErrorEntity apiError, HttpStatus responseStatus, String responseBody) {
        super(apiError, responseStatus, responseBody);
    }
}
