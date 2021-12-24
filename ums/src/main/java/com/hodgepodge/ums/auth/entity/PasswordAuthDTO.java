package com.hodgepodge.ums.auth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * <p>
 * Title: 密码认证请求实体
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2021年12月24日
 * @since 1.8
 */
@ApiModel("密码认证请求实体")
@Data
public class PasswordAuthDTO extends AbstractAuthDTO{

    @ApiModelProperty(hidden = true)
    private final String grantType = "password";

    @NotBlank
    @ApiModelProperty("帐号")
    private String username;

    @NotBlank
    @ApiModelProperty("密码")
    private String password;

    @Override
    public Map<String, String> getParams() {
        Map<String, String> parent = super.getParams();
        parent.put("username", username);
        parent.put("password", password);
        return parent;
    }
}
