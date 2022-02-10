package com.hodgepodge.security.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hodgepodge.security.entity.UserAuthority;
import com.hodgepodge.security.entity.UserDO;

import java.util.List;

/**
 * 用户表服务接口
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
public interface IUserService extends IService<UserDO> {

    /**
     * <p>
     * Title: 根据用户名获取用户
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param username
     * @return com.hodgepodge.ums.entity.UserDO
     * @author 刘小杰
     * @date 2022年01月26日
     * @since 1.8
     */
    UserDO getOneByUsername(String username);

    /**
     * <p>
     * Title: 获取用户权限
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param userId 用户ID
     * @return java.util.List<com.hodgepodge.ums.auth.entity.UserAuthority>
     * @author 刘小杰
     * @date 2022年01月27日
     * @since 1.8
     */
    List<UserAuthority> selectUserAuthorities(Long userId);
}