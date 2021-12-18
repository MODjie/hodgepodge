package com.hodgepodge.ums.controller;

import com.hodgepodge.ums.service.UmsRoleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色表(UmsRole)服务
 *
 * @author makejava
 * @since 2021-12-18 17:17:17
 */
@Api(tags = "角色表")
@RestController
@RequestMapping
public class UmsRoleController {
    /**
     * 服务对象
     */
    @Resource
    private UmsRoleService umsRoleService;

}