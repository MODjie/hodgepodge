package com.hodgepodge.ums.controller;

import com.hodgepodge.ums.service.UserInfoService;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户信息表服务
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Api(tags = "用户信息表")
@RestController
@RequestMapping("user-info")
public class UserInfoController {
    /**
     * 服务对象
     */
    @Resource
    private UserInfoService userInfoService;

//    @PreAuthorize("hasAuthority('user:view')")
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}