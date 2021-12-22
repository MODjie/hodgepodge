package com.hodgepodge.ums.controller;

import com.hodgepodge.ums.service.RolePermissionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色权限关系表服务
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Api(tags = "角色权限关系表")
@RestController
@RequestMapping
public class RolePermissionController {
    /**
     * 服务对象
     */
    @Resource
    private RolePermissionService rolePermissionService;

}