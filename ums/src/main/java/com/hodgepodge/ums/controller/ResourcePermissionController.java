package com.hodgepodge.ums.controller;

import com.hodgepodge.ums.service.ResourcePermissionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 权限表服务
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Api(tags = "权限表")
@RestController
@RequestMapping
public class ResourcePermissionController {
    /**
     * 服务对象
     */
    @Resource
    private ResourcePermissionService resourcePermissionService;

}