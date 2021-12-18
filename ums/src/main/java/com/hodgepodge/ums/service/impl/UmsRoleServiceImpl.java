package com.hodgepodge.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodgepodge.ums.entity.UmsRole;
import com.hodgepodge.ums.mapper.UmsRoleMapper;
import com.hodgepodge.ums.service.UmsRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色表(UmsRole)服务接口实现
 *
 * @author makejava
 * @since 2021-12-18 17:17:17
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements UmsRoleService {

}