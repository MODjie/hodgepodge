package com.hodgepodge.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodgepodge.ums.entity.RoleDO;
import com.hodgepodge.ums.mapper.RoleMapper;
import com.hodgepodge.ums.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 角色表服务接口实现
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements RoleService {

}