package com.hodgepodge.ums.controller;

import com.hodgepodge.ums.service.UmsUserRoleRelationService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户角色关系表(UmsUserRoleRelation)服务
 *
 * @author makejava
 * @since 2021-12-18 17:17:17
 */
@Api(tags = "用户角色关系表")
@RestController
@RequestMapping
public class UmsUserRoleRelationController {
    /**
     * 服务对象
     */
    @Resource
    private UmsUserRoleRelationService umsUserRoleRelationService;

}