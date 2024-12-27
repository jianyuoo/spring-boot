package air.admin.spring_boot.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisObjectService <T>{

    @Autowired
    private RedisTemplate<String, T> redisTemplate;


    public void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public T get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean expire(String s, int i, TimeUnit timeUnit) {
        return redisTemplate.expire(s,i,timeUnit);
    }
}
