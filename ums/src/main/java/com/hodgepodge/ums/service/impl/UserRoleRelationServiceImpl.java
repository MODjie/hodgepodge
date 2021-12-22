package com.hodgepodge.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodgepodge.ums.entity.UserRoleRelationDO;
import com.hodgepodge.ums.mapper.UserRoleRelationMapper;
import com.hodgepodge.ums.service.UserRoleRelationService;
import org.springframework.stereotype.Service;

/**
 * 用户角色关系表服务接口实现
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Service
public class UserRoleRelationServiceImpl extends ServiceImpl<UserRoleRelationMapper, UserRoleRelationDO> implements UserRoleRelationService {

}