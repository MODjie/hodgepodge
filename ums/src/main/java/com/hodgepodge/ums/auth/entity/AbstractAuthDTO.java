package com.hodgepodge.ums.auth.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Title: 抽象认证实体
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2021年12月24日
 * @since 1.8
 */
public abstract class AbstractAuthDTO {
    public static final String GRANT_TYPE_KEY = "grant_type";
    public static final String SCOPE_KEY = "scope";
    public static final String CLIENT_ID_KEY = "client_id";
    public static final String CLIENT_SECRET_KEY = "client_secret";

    @ApiModelProperty(hidden = true)
    private final String scope = "all";
    @ApiModelProperty(hidden = true)
    private String clientId;
    @ApiModelProperty(hidden = true)
    private String clientSecret;

    public String getScope() {
        return scope;
    }

    public String getClientId() {
        return clientId;
    }

    public AbstractAuthDTO setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public AbstractAuthDTO setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    /**
     * <p>
     * Title: 返回授权类型
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return java.lang.String
     * @author 刘小杰
     * @date 2021年12月24日
     * @since 1.8
     */
    public abstract String getGrantType();

    public Map<String, String> getParams() {
        String grantType = getGrantType();
        return new HashMap<String,String>(16) {
            private static final long serialVersionUID = 7894196056491422614L;
            {
                put(GRANT_TYPE_KEY, grantType);
                put(SCOPE_KEY, scope);
                put(CLIENT_ID_KEY, clientId);
                put(CLIENT_SECRET_KEY, clientSecret);
            }
        };
    }
}
