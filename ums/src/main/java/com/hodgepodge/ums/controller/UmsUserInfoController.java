package com.hodgepodge.ums.controller;

import com.hodgepodge.ums.service.UmsUserInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户信息表(UmsUserInfo)服务
 *
 * @author makejava
 * @since 2021-12-18 17:15:22
 */
@Api(tags = "用户信息表")
@RestController
@RequestMapping
public class UmsUserInfoController {
    /**
     * 服务对象
     */
    @Resource
    private UmsUserInfoService umsUserInfoService;

}