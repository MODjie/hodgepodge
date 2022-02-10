package com.hodgepodge.security.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodgepodge.security.entity.UserAuthority;
import com.hodgepodge.security.entity.UserDO;
import com.hodgepodge.security.mapper.IUserMapper;
import com.hodgepodge.security.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户表服务接口实现
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Service("iUserService")
public class IUserServiceImpl extends ServiceImpl<IUserMapper, UserDO> implements IUserService {

    @Override
    public UserDO getOneByUsername(String username) {
        return this.lambdaQuery().eq(UserDO::getUsername, username).one();
    }

    @Override
    public List<UserAuthority> selectUserAuthorities(Long userId) {
        return this.getBaseMapper().selectUserAuthorities(userId);
    }
}