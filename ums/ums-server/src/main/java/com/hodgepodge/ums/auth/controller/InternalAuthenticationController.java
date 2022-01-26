package com.hodgepodge.ums.auth.controller;

import com.hodgepodge.ums.auth.service.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * Title: 认证中心控制器
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月25日
 * @since 1.8
 */

@RestController
@RequestMapping("/internal-auth")
public class InternalAuthenticationController {

    @Resource
    private AuthenticationService authenticationService;

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
    @GetMapping("/validateToken")
    public Boolean validateToken(@RequestParam("token") String token) {
        return authenticationService.validateToken(token);
    }

}


