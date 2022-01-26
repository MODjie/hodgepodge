package com.hodgepodge.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * Title: BaseException
 * </p>
 * <p>
 * Description: 基础异常实体
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月13日
 * @since 1.8
 */
@Data
public class BaseErrorEntity implements Serializable {
    private static final long serialVersionUID = 5060321993822631096L;

    @ApiModelProperty("错误编码")
    private String code;

    @ApiModelProperty("错误消息")
    private String msg;

    @ApiModelProperty("错误主体")
    private String subject;
}
