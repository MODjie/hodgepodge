package com.hodgepodge.ums.controller;

import com.hodgepodge.ums.service.ResourceService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 资源表服务
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Api(tags = "资源表")
@RestController
@RequestMapping
public class ResourceController {
    /**
     * 服务对象
     */
    @Resource
    private ResourceService resourceService;

}