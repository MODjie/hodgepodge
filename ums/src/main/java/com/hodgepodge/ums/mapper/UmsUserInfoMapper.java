package com.hodgepodge.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hodgepodge.ums.entity.UmsUserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息表(UmsUserInfo)Mapper
 *
 * @author makejava
 * @since 2021-12-18 17:15:22
 */
@Mapper
public interface UmsUserInfoMapper extends BaseMapper<UmsUserInfo> {

}