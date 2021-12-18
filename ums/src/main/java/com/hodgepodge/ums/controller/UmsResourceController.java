package com.hodgepodge.ums.controller;

import com.hodgepodge.ums.service.UmsResourceService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 资源表(UmsResource)服务
 *
 * @author makejava
 * @since 2021-12-18 17:17:17
 */
@Api(tags = "资源表")
@RestController
@RequestMapping
public class UmsResourceController {
    /**
     * 服务对象
     */
    @Resource
    private UmsResourceService umsResourceService;

}