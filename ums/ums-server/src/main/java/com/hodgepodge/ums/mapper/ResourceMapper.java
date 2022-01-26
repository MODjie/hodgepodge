package com.hodgepodge.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hodgepodge.ums.entity.ResourceDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资源表Mapper
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Mapper
public interface ResourceMapper extends BaseMapper<ResourceDO> {

}