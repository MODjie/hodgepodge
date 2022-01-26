package com.hodgepodge.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodgepodge.ums.entity.UserDO;
import com.hodgepodge.ums.mapper.UserMapper;
import com.hodgepodge.ums.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表服务接口实现
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Override
    public UserDO getOneByUsername(String username) {
        return this.lambdaQuery().eq(UserDO::getUsername, username).one();
    }
}