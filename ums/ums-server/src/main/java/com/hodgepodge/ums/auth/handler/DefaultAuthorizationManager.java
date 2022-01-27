package com.hodgepodge.ums.auth.handler;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Supplier;

/**
 * <p>
 * Title: 默认的授权管理器（用于鉴权）
 * </p>
 * <p>
 * Description: 判断当前用户是否有权限访问待请求的资源
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月27日
 * @since 1.8
 */
public class DefaultAuthorizationManager<HttpServletRequest> implements AuthorizationManager<HttpServletRequest> {

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authenticationSupplier, HttpServletRequest request) {
        Authentication authentication = authenticationSupplier.get();
        if (authentication == null){
            return null;
        }
        AuthorizationDecision result = new AuthorizationDecision(true);
        return result;
    }
}
