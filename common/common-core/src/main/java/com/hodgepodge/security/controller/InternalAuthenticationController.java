package com.hodgepodge.security.controller;

import com.hodgepodge.security.service.MyAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MyAuthenticationService myAuthenticationService;

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
        return myAuthenticationService.validateToken(token);
    }

}


