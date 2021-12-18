package com.hodgepodge.ums.controller;

import com.hodgepodge.ums.service.UmsRolePermissionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色权限关系表(UmsRolePermission)服务
 *
 * @author makejava
 * @since 2021-12-18 17:17:17
 */
@Api(tags = "角色权限关系表")
@RestController
@RequestMapping
public class UmsRolePermissionController {
    /**
     * 服务对象
     */
    @Resource
    private UmsRolePermissionService umsRolePermissionService;

}