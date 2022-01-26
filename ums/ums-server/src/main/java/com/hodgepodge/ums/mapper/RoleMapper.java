package com.hodgepodge.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hodgepodge.ums.entity.RoleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表Mapper
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {

}