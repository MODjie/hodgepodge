package com.hodgepodge.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hodgepodge.ums.entity.UmsUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表(UmsUser)Mapper
 *
 * @author makejava
 * @since 2021-12-18 11:04:24
 */
@Mapper
public interface UmsUserMapper extends BaseMapper<UmsUser> {

}