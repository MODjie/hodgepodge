package com.hodgepodge.ums.controller;

import com.hodgepodge.ums.service.UmsResourcePermissionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 权限表(UmsResourcePermission)服务
 *
 * @author makejava
 * @since 2021-12-18 17:17:17
 */
@Api(tags = "权限表")
@RestController
@RequestMapping
public class UmsResourcePermissionController {
    /**
     * 服务对象
     */
    @Resource
    private UmsResourcePermissionService umsResourcePermissionService;

}