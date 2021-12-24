package com.hodgepodge.ums.auth.controller;

import com.hodgepodge.ums.auth.entity.PasswordAuthDTO;
import com.hodgepodge.ums.auth.entity.RefreshTokenDTO;
import com.hodgepodge.ums.auth.entity.TokenVO;
import com.hodgepodge.ums.auth.service.InternalAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * Title: PasswordAuthController
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2021年12月24日
 * @since 1.8
 */
@RestController
@RequestMapping("/system")
@Api("密码登录")
public class PasswordAuthController {

    @Resource
    private InternalAuthService internalAuthService;

    @ApiOperation("密码登录")
    @PostMapping("/login")
    public TokenVO passwordLogin(@RequestBody @Valid PasswordAuthDTO dto){
        return internalAuthService.passwordLogin(dto);
    }

    @ApiOperation("刷新令牌")
    @PostMapping("/refreshToken")
    public TokenVO refreshToken(@RequestBody @Valid RefreshTokenDTO dto){
        return internalAuthService.refreshToken(dto);
    }

}
