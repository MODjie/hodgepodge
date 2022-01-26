package com.hodgepodge.ums.auth.filter;

import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * Title: AuthenticationFilter
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月26日
 * @since 1.8
 */
public class DefaultAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final String usernameParameter = "username";
    private final String passwordParameter = "password";


    public DefaultAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

//        if (this.postOnly && !request.getMethod().equals("POST")) {
//            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
//        }
//        String username = request.getParameter(this.usernameParameter);
//        username = (username != null) ? username : "";
//        username = username.trim();
//        String password = request.getParameter(this.passwordParameter);
//        password = (password != null) ? password : "";
//        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
//        // Allow subclasses to set the "details" property
//        setDetails(request, authRequest);
//        return this.getAuthenticationManager().authenticate(authRequest);
        return null;
    }
}
