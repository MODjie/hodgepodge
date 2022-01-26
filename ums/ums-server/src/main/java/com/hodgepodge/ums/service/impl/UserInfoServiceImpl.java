package com.hodgepodge.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodgepodge.ums.entity.UserInfoDO;
import com.hodgepodge.ums.mapper.UserInfoMapper;
import com.hodgepodge.ums.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * 用户信息表服务接口实现
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoDO> implements UserInfoService {

}