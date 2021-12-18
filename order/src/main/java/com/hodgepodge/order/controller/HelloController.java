package com.hodgepodge.order.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户表(UmsUser)服务
 *
 * @author makejava
 * @since 2021-12-18 11:04:24
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping("/hello")
public class HelloController {


    @ApiOperation("hello order")
    @GetMapping
    public String hello(){
        return "hello order";
    }

}