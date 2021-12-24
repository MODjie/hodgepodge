package com.hodgepodge.ums.auth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

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
public class TokenVO {

    @ApiModelProperty("token值")
    private String accessToken;

    @ApiModelProperty("token类型")
    private String tokenType;

    @ApiModelProperty("刷新token值")
    private String refreshToken;

    @ApiModelProperty("有效时长(秒)")
    private Long expiresIn;

    @ApiModelProperty("过期时间")
    private Date expiresTime;

    @ApiModelProperty("访问域")
    private String scope;

}
