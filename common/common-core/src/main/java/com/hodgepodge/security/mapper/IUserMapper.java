package com.hodgepodge.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hodgepodge.security.entity.UserAuthority;
import com.hodgepodge.security.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户表Mapper
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Mapper
public interface IUserMapper extends BaseMapper<UserDO> {

    /**
     * <p>
     * Title: 获取用户权限
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param userId
     * @return java.util.List<com.hodgepodge.ums.auth.entity.UserAuthority>
     * @author 刘小杰
     * @date 2022年01月27日
     * @since 1.8
     */
    List<UserAuthority> selectUserAuthorities(Long userId);
}