package com.rock.miaosha.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;
/**
*@author rock
*@Date 2019/9/17 23:14
*@param 
*@return 
*
*/
@Component
public class RedisUtils {
    @Autowired
    private StringRedisTemplate template;

    public String get(String key){
        return template.opsForValue().get(key);
    }
    public void set(String key,String value){
        template.opsForValue().set(key,value);
    }
    public boolean expire(String key, long timeout){
        Boolean expire = template.expire(key, timeout, TimeUnit.SECONDS);
        if (expire) {
            return true;
        }
        return false;
    }
}
