package com.hodgepodge.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodgepodge.ums.entity.UmsUser;
import com.hodgepodge.ums.mapper.UmsUserMapper;
import com.hodgepodge.ums.service.UmsUserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(UmsUser)服务接口实现
 *
 * @author makejava
 * @since 2021-12-18 11:04:24
 */
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements UmsUserService {

}