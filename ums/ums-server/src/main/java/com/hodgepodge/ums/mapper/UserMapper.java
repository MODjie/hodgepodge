package com.hodgepodge.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hodgepodge.ums.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表Mapper
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

}