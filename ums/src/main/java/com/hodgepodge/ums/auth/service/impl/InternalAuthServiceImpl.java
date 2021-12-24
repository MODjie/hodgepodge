package com.hodgepodge.ums.auth.service.impl;

import com.hodgepodge.ums.auth.entity.PasswordAuthDTO;
import com.hodgepodge.ums.auth.entity.RefreshTokenDTO;
import com.hodgepodge.ums.auth.entity.TokenVO;
import com.hodgepodge.ums.auth.service.InternalAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

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
@Service
public class InternalAuthServiceImpl implements InternalAuthService {

    @Resource
    private TokenEndpoint tokenEndpoint;

    @Override
    public TokenVO passwordLogin(PasswordAuthDTO dto) {
        dto.setClientId("admin");
        dto.setClientSecret("e10adc3949ba59abbe56e057f20f883e");
        return this.createToken(dto.getClientId(),dto.getParams());
    }

    @Override
    public TokenVO refreshToken(RefreshTokenDTO dto) {
        return null;
    }

    protected TokenVO createToken(String clientId, Map<String, String> params) {
        return Optional
                .ofNullable(delegate(clientId, params))
                .filter(ResponseEntity::hasBody)
                .map(ResponseEntity::getBody)
                .map(token -> {
                    TokenVO tokenVO = new TokenVO();
                    tokenVO.setTokenType(token.getTokenType());
                    tokenVO.setAccessToken(token.getValue());
                    tokenVO.setRefreshToken(Optional
                            .ofNullable(token.getRefreshToken())
                            .map(OAuth2RefreshToken::getValue)
                            .orElse(null));
                    tokenVO.setScope(StringUtils.collectionToCommaDelimitedString(token.getScope()));
                    tokenVO.setExpiresTime(token.getExpiration());
                    Date expiration = token.getExpiration();
                    if (expiration != null) {
                        tokenVO.setExpiresIn((expiration.getTime() - System.currentTimeMillis()) / 1000);
                    }
                    return tokenVO;
                })
                .orElseThrow(() ->  new RuntimeException("认证失败"));
    }

    protected ResponseEntity<OAuth2AccessToken> delegate(String clientId, Map<String, String> params) {
        try {
            return tokenEndpoint.postAccessToken(new UsernamePasswordAuthenticationToken(clientId, null, Collections.emptyList()), params);
        } catch (HttpRequestMethodNotSupportedException e) {
            return null;
        }
    }
}
