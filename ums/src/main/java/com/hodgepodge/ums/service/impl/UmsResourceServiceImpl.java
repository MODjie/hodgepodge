package com.hodgepodge.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodgepodge.ums.entity.UmsResource;
import com.hodgepodge.ums.mapper.UmsResourceMapper;
import com.hodgepodge.ums.service.UmsResourceService;
import org.springframework.stereotype.Service;

/**
 * 资源表(UmsResource)服务接口实现
 *
 * @author makejava
 * @since 2021-12-18 17:17:17
 */
@Service
public class UmsResourceServiceImpl extends ServiceImpl<UmsResourceMapper, UmsResource> implements UmsResourceService {

}