package com.hodgepodge.ums.auth.handler;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTUtil;
import com.hodgepodge.ums.auth.entity.TokenVO;
import com.hodgepodge.ums.auth.entity.UserDetailsVO;
import com.hodgepodge.ums.auth.util.AuthUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Title: DefaultAuthenticationSuccessHandler
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author <刘小杰>
 * @date 2022年01月26日
 * @since 1.8
 */
public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetailsVO details = (UserDetailsVO) authentication.getPrincipal();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        TokenVO token = this.createToken(details);
        //将token存入redis，并设置过期时间
        Long userId = details.getUserId();
        String username = details.getUsername();
        redisTemplate.opsForValue().set(AuthUtil.getTokenKeyInRedis(userId,username),token.getToken(), Duration.ofMillis(token.getEffectiveTime()));
        //将生成的token输出到响应的body中
        String body = JSONUtil.toJsonStr(token);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(body.getBytes());
    }

    /**
     * <p>
     * Title: 生成令牌
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param details 用户信息
     * @return com.hodgepodge.ums.auth.entity.TokenVO
     * @author 刘小杰
     * @date 2022年01月26日
     * @since 1.8
     */
    private TokenVO createToken(UserDetailsVO details) {
        Long userId = details.getUserId();
        String username = details.getUsername();
        //token过期时间默认为一天
        Long effectiveTime = Long.valueOf(1000 * 60 * 60 * 24);
        Long expiredTime = System.currentTimeMillis() + effectiveTime;
        Map<String, Object> map = new HashMap<String, Object>(10) {
            private static final long serialVersionUID = 1L;
            {
                put("userId", userId);
                put("username", username);
                put("expiredTime", expiredTime);
            }
        };
        String token = JWTUtil.createToken(map, AuthUtil.getSecretKey(userId, username).getBytes());
        return new TokenVO(token,expiredTime,effectiveTime);
    }
}
