package com.hodgepodge.security.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 * Title: 认证Token
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author <刘小杰>
 * @date 2021年12月23日
 * @since 1.8
 */
@Data
@ApiModel(description = "认证Token")
@AllArgsConstructor
public class TokenVO {

    @ApiModelProperty("token值")
    private String token;

    @ApiModelProperty("过期时间")
    private Long expiredTime;

    @ApiModelProperty("有效时间")
    private Long effectiveTime;
}
