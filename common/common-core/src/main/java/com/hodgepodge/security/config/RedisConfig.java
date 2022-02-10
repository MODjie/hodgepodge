package com.hodgepodge.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * <p>
 * Title: RedisConfig
 * </p>
 * <p>
 * Description:
 * </p>
 *
 * @author <刘小杰>
 * @date 2022年01月27日
 * @since 1.8
 */
@Configuration
public class RedisConfig {

    @Resource
    public void redisKeySerializer(RedisTemplate redisTemplate){
        //解决redis的中文乱码问题
        redisTemplate.setKeySerializer(new StringRedisSerializer());
    }


}
