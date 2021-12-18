package com.hodgepodge.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodgepodge.ums.entity.UmsUserInfo;
import com.hodgepodge.ums.mapper.UmsUserInfoMapper;
import com.hodgepodge.ums.service.UmsUserInfoService;
import org.springframework.stereotype.Service;

/**
 * 用户信息表(UmsUserInfo)服务接口实现
 *
 * @author makejava
 * @since 2021-12-18 17:15:22
 */
@Service
public class UmsUserInfoServiceImpl extends ServiceImpl<UmsUserInfoMapper, UmsUserInfo> implements UmsUserInfoService {

}