package com.hodgepodge.ums.controller;

import com.hodgepodge.ums.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户表服务
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Api(tags = "用户表")
@RestController
@RequestMapping
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

}