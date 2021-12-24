package com.hodgepodge.ums.auth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * <p>Title: PasswordAuthDTO</p>
 * <p>Description: </p>
 *
 * @author 刘小杰
 * @date 2020年12月24日
 * @since 1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "刷新Token请求实体")
public class RefreshTokenDTO extends AbstractAuthDTO {

    @ApiModelProperty(hidden = true)
    private final String grantType = "refresh_token";

    @NotBlank
    @ApiModelProperty("刷新Token")
    private String refreshToken;

    @Override
    public Map<String, String> getParams() {
        Map<String, String> parent = super.getParams();
        parent.put("refresh_token", refreshToken);
        return parent;
    }
}
