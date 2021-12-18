package com.hodgepodge.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hodgepodge.ums.entity.UmsRolePermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色权限关系表(UmsRolePermission)Mapper
 *
 * @author makejava
 * @since 2021-12-18 17:17:17
 */
@Mapper
public interface UmsRolePermissionMapper extends BaseMapper<UmsRolePermission> {

}