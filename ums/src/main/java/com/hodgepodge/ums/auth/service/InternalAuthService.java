package com.hodgepodge.ums.auth.service;

import com.hodgepodge.ums.auth.entity.PasswordAuthDTO;
import com.hodgepodge.ums.auth.entity.RefreshTokenDTO;
import com.hodgepodge.ums.auth.entity.TokenVO;

/**
 * <p>
 * Title: 内部认证服务
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2021年12月24日
 * @since 1.8
 */
public interface InternalAuthService {

    /**
     * <p>
     * Title: 密码登录
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param dto 密码登录参数
     * @return com.hodgepodge.ums.auth.entity.TokenVO
     * @author 刘小杰
     * @date 2021年12月24日
     * @since 1.8
     */
    TokenVO passwordLogin(PasswordAuthDTO dto);

    /**
     * <p>
     * Title: 刷新令牌
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param dto 刷新令牌参数
     * @return com.hodgepodge.ums.auth.entity.TokenVO
     * @author 刘小杰
     * @date 2021年12月24日
     * @since 1.8
     */
    TokenVO refreshToken(RefreshTokenDTO dto);
}
