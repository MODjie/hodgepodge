package com.hodgepodge.ums.auth.filter;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTUtil;
import com.hodgepodge.ums.auth.entity.TokenVO;
import com.hodgepodge.ums.auth.entity.UserDetailsVO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Title: 生成令牌过滤器
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月26日
 * @since 1.8
 */
public class GenerateTokenFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
            chain.doFilter(request,response);
            return;
        }
        UserDetailsVO details = (UserDetailsVO) authentication.getDetails();
        if (details == null){
            chain.doFilter(request,response);
            return;
        }
        TokenVO token = this.createToken(details);
        PrintWriter writer = response.getWriter();
//        JSONUtil.toJson
//        writer.print(JSONObject.toJSONString(token, SerializerFeature.WriteMapNullValue,
//                SerializerFeature.WriteDateUseDateFormat));
        writer.close();
        response.flushBuffer();
        chain.doFilter(request,response);
    }

    /**
     * <p>
     * Title: 生成令牌
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param details
     * @return com.hodgepodge.ums.auth.entity.TokenVO
     * @author 刘小杰
     * @date 2022年01月26日
     * @since 1.8
     */
    private TokenVO createToken(UserDetailsVO details) {
        Long userId = details.getUserId();
        String username = details.getUsername();
        Long expiredTime = System.currentTimeMillis() + 1000 * 60 * 60 * 24;
        Map<String, Object> map = new HashMap<String, Object>(10) {
            private static final long serialVersionUID = 1L;
            {
                put("userId", userId);
                put("username", username);
                put("expiredTime", expiredTime);
            }
        };
        String token = JWTUtil.createToken(map, com.hodgepodge.ums.auth.util.JWTUtil.getSecretKey(userId, username).getBytes());
        return new TokenVO(token,new Date(expiredTime));
    }
}
