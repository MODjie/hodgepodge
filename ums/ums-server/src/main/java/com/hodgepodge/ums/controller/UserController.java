package com.hodgepodge.ums.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户表服务
 *
 * @author 刘小杰
 * @since 2021-12-22 13:07:49
 */
@Api(tags = "用户表")
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}