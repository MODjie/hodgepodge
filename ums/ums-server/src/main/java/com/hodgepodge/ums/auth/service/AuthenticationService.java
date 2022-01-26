package com.hodgepodge.ums.auth.service;

import com.hodgepodge.ums.auth.entity.TokenVO;

/**
 * <p>
 * Title: 认证中心服务
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月25日
 * @since 1.8
 */
public interface AuthenticationService {
    /**
     * <p>
     * Title: 校验token令牌
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param token 令牌
     * @return java.lang.Boolean
     * @author 刘小杰
     * @date 2022年01月25日
     * @since 1.8
     */
    Boolean validateToken(String token);

}
