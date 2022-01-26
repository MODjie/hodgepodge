package com.hodgepodge.ums.auth.service.impl;

import cn.hutool.jwt.JWT;
import com.hodgepodge.ums.auth.service.AuthenticationService;
import com.hodgepodge.ums.auth.util.AuthUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * <p>
 * Title: 认证中心服务实现类
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author 刘小杰
 * @date 2022年01月25日
 * @since 1.8
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Boolean validateToken(String token) {
        if (!StringUtils.hasText(token)){
            return false;
        }
        //解析token
        JWT jwt;
        try {
            jwt = cn.hutool.jwt.JWTUtil.parseToken(token);
        }catch (Exception e){
            return false;
        }
        //校验token是否过期
        Long expiredTime = (Long) jwt.getPayload("expiredTime");
        if (expiredTime < System.currentTimeMillis()){
           return false;
        }
        //获取用户信息
        Long userId = Long.valueOf((Integer) jwt.getPayload("userId"));
        String username = (String) jwt.getPayload("username");
        //判断redis中是否存在此token
        String cacheToken = (String) redisTemplate.opsForValue().get(AuthUtil.getTokenKeyInRedis(userId,username));
        if (!StringUtils.hasText(cacheToken)){
            return false;
        }
        if (!cacheToken.equals(token)){
            return false;
        }
        return true;
    }
}
