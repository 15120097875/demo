package com.mylock.scope;

import com.mylock.annotation.Ide;
import com.mylock.handler.CheckException;
import com.mylock.util.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @ClassName CloudIdempotentConfiguration
 * @Description TODO
 * @Author a
 * @Date 2021/2/23 16:41
 */
@Slf4j
@Aspect
@Component
public class CloudIdempotentConfiguration {
    @Autowired
    private RedisLock redisLockUtils;

    @Pointcut("@annotation(com.mylock.annotation.Ide)")
    public void idePointCut() {
    }


    @Around("idePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 使用分布式锁 机制-实现
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Ide ide = method.getAnnotation(Ide.class);
        int lockSeconds = ide.lockTime();
//        String userId  = SecurityUtils.getUser().getId().toString();

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
//        AssertUtils.notNull(request, "request can not null");
//
        String userId = request.getParameter("userId");
//        // 获取请求的凭证，本项目中使用的JWT,可对应修改
        String token = request.getHeader("Authorization");
        String requestURI = request.getRequestURI();
//
        String key = getIdeKey(token, requestURI);
//        String clientId = CmUtil.getUUID();

        // 获取锁
        boolean lock = redisLockUtils.addRedisLock(key, userId, lockSeconds);
        log.info("tryLock key = [{}], clientId = [{}]", key, userId);

        if (lock) {
            log.info("tryLock success, key = [{}], clientId = [{}]", key, userId);
            // 获取锁成功
            Object result;
            try {
                // 执行进程
                result = joinPoint.proceed();
            } finally {
                // 解锁
                redisLockUtils.unlock(key, userId);
                log.info("releaseLock success, key = [{}], clientId = [{}]", key, userId);
            }
            return result;
        } else {
            // 获取锁失败，认为是重复提交的请求
            log.info("tryLock fail, key = [{}]", key);
            throw new CheckException("重复请求，请稍后再试!");
        }
    }

    private String getIdeKey(String token, String requestURI) {
        return token + requestURI;
    }

}
