package com.hodgepodge.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hodgepodge.ums.entity.UserDO;


/**
 * 用户表服务接口
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
public interface UserService extends IService<UserDO> {

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

}