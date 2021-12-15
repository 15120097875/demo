package com.mylock.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component("RedisLock")
public class RedisLock {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String UNLOCK_LUA;

    private static final int RETRY_TIME = 200;

    static {
        UNLOCK_LUA = " if redis.call('get',KEYS[1]) == ARGV[1] " +
                " then " +
                "    return redis.call('del',KEYS[1]) " +
                " else " +
                "    return 0 " +
                " end ";
    }

    // 有200ms重试机制
    public boolean addRedisLock(String lockKey, String requestId, long expireTime) {
        return addRedisLock(lockKey, requestId, 500, expireTime);
    }

    public boolean lock(String lockKey, String requestId, long expireTime) {
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, requestId, expireTime, TimeUnit.SECONDS);
        return result != null && result;
    }

    public boolean unlock(String lockKey, Object value) {
        RedisCallback<Boolean> callback = redisConnection -> redisConnection.eval(UNLOCK_LUA.getBytes(), ReturnType.BOOLEAN, 1, lockKey.getBytes(), String.valueOf(value).getBytes());
        Boolean result = stringRedisTemplate.execute(callback);
        return result != null && result;
    }

    /**
     * 加锁
     *
     * @param lockKey
     * @param waitTime   等待时间(毫秒)
     * @param expireTime 强制锁释放时间
     * @return
     */
    public boolean addRedisLock(String lockKey, String requestId, int waitTime, long expireTime) {
        int alreadyWaitTime = 0;
        while (waitTime > alreadyWaitTime) {
            if (lock(lockKey, requestId, expireTime)) {
                return true;
            } else {
                try {
                    Thread.sleep(RETRY_TIME);
                } catch (InterruptedException e) {
                    log.warn(e.getMessage(), e);

                    return false;
                }
            }

            alreadyWaitTime += RETRY_TIME;
        }

        return false;
    }

    /***
     *
     * @param key
     * @return
     */
    public String get(String key) {
        if (StrUtil.isEmpty(key)) {
            return null;
        }
        return stringRedisTemplate.opsForValue().get(key);
    }

    /***
     * 利用redis原子性
     * @param key
     * @param value
     * @return
     */
    public long increment(String key, long value) {
        Long increment = stringRedisTemplate.opsForValue().increment(key, value);
        assert increment == null : "增量失败";
        return increment.longValue();
    }

    private static final String EXCLUSION_UNLOCK_LUA =
            " if redis.call('get', KEYS[2]) == false " +
            " then " +
            " redis.call('set', KEYS[1], ARGV[1], 'ex', ARGV[2]) " +
            " return 1 " +
            " else " +
            " return 0 " +
            " end ";

    public boolean addExclusionLock(String lockKey1, String lockKey2) {
        return addExclusionLock(lockKey1, lockKey2, String.valueOf(System.currentTimeMillis()));
    }

    public boolean addExclusionLock(String lockKey1, String lockKey2, Object value) {
        return addExclusionLock(lockKey1, lockKey2, value, 60);
    }

    /**
     * 60秒更新
     *
     * @param lockKey1 判断
     * @param lockKey2 设置
     * @return true成功，false失败
     */
    public boolean addExclusionLock(String lockKey1, String lockKey2, Object value, long expireTime) {
        int alreadyWaitTime = 0;
        int waitTime = 500;
        RedisCallback<Boolean> callback = redisConnection -> redisConnection.eval(EXCLUSION_UNLOCK_LUA.getBytes(), ReturnType.BOOLEAN, 2, lockKey1.getBytes(), lockKey2.getBytes(), String.valueOf(value).getBytes(), String.valueOf(expireTime).getBytes());
        while (waitTime > alreadyWaitTime) {
            Boolean exeResult = stringRedisTemplate.execute(callback);
            if (exeResult != null && exeResult) {
                return true;
            } else {
                try {
                    Thread.sleep(RETRY_TIME);
                } catch (InterruptedException e) {
                    log.warn(e.getMessage(), e);
                    return false;
                }
            }
            alreadyWaitTime += RETRY_TIME;
        }
        return false;
    }

}
