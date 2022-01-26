package com.hodgepodge.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodgepodge.ums.entity.RolePermissionDO;
import com.hodgepodge.ums.mapper.RolePermissionMapper;
import com.hodgepodge.ums.service.RolePermissionService;
import org.springframework.stereotype.Service;

/**
 * 角色权限关系表服务接口实现
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermissionDO> implements RolePermissionService {

}