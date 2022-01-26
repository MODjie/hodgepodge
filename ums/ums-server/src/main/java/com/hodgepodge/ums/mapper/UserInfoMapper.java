package com.hodgepodge.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hodgepodge.ums.entity.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息表Mapper
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoDO> {

}