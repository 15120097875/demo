package com.mylock.scope;

import com.mylock.annotation.Exclusion;
import com.mylock.util.R;
import com.mylock.util.RedisLock;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 *  @author liqing
 *  @date 2021/6/21
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class ExclusionAspect {

//    private final StringRedisTemplate stringRedisTemplate;

    private final RedisLock redisLock;

    private static final String KEY_SIGN_0 = "0";
    private static final String KEY_SIGN_1 = "1";

    @SneakyThrows
    @Around("@annotation(exclusion)")
    public Object pointcut(ProceedingJoinPoint joinPoint, Exclusion exclusion) {
        log.info("-----------------  ExclusionAspect执行开始  -------------");

        String value = exclusion.value();

        String key0 = "user";
        String key1 = "orther";

        String errMsg;
        boolean result = false;
        String delKey = null;
        long time = System.currentTimeMillis();
        try {
            try {
                if (KEY_SIGN_0.equals(value)) {
                    // 支付时判断菜谱是否在更新
                    result = redisLock.addExclusionLock(key0, key1, time, 1000);
                    errMsg = key0 + " 加锁失败";
                    delKey = key0;
                } else {
                    // 菜谱更新时判断是否在支付
                    result = redisLock.addExclusionLock(key1, key0, time, 1000);
                    errMsg = key1 + " 加锁失败";
                    delKey = key1;
                }
                if (!result) {
                    log.info("-----------------  " + errMsg + "  -----------------");
                    return R.failed(errMsg);
                }
            } catch (Exception e) {
                log.error("业务代码异常", e);
            }
            return joinPoint.proceed();
        } finally {
            // 业务执行完成，删除自身的key
            if (result) {
//                stringRedisTemplate.delete(delKey);
                boolean unlock = redisLock.unlock(delKey, time);
                log.info("释放锁结果：{}", unlock);
            }
            log.info("-----------------  ExclusionAspect执行结束  -------------");
        }
    }

}
