package com.hodgepodge.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * Title: Error
 * </p>
 * <p>
 * Description: 服务统一异常结果响应实体
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月13日
 * @since 1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "服务异常响应实体")
public class ErrorEntity extends BaseErrorEntity {
    private static final long serialVersionUID = 6651562229106660995L;

    @ApiModelProperty("状态码")
    private int statusCode;

    @ApiModelProperty("扩展异常信息")
    private List<BaseErrorEntity> extras;

    @ApiModelProperty("错误链路踪迹")
    private ErrorEntity trace;

    @ApiModelProperty("是否异常标识，永远为true")
    private boolean apiErr = true;
}
