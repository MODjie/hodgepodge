package com.hodgepodge.ums.controller;

import com.hodgepodge.ums.service.UmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户表(UmsUser)服务
 *
 * @author makejava
 * @since 2021-12-18 11:04:24
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("ums-user")
public class UmsUserController {
    /**
     * 服务对象
     */
    @Resource
    private UmsUserService umsUserService;


    @ApiOperation("hello world")
    @GetMapping
    public String hello(){
        return "hello world";
    }

}