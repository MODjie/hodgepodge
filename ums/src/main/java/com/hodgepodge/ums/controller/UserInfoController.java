package com.hodgepodge.ums.controller;

import com.hodgepodge.ums.service.UserInfoService;
import io.swagger.annotations.Api;
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
@RequestMapping
public class UserInfoController {
    /**
     * 服务对象
     */
    @Resource
    private UserInfoService userInfoService;

}