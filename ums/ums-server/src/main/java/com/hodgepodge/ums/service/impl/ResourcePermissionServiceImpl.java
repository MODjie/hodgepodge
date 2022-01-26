package com.hodgepodge.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hodgepodge.ums.entity.ResourcePermissionDO;
import com.hodgepodge.ums.mapper.ResourcePermissionMapper;
import com.hodgepodge.ums.service.ResourcePermissionService;
import org.springframework.stereotype.Service;

/**
 * 权限表服务接口实现
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Service
public class ResourcePermissionServiceImpl extends ServiceImpl<ResourcePermissionMapper, ResourcePermissionDO> implements ResourcePermissionService {

}